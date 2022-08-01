package controller;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.LogUtil;
import util.RequestMapping;

/**
 * DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String TAG = "DispatcherServlet";
    private String charset = null;

    public DispatcherServlet() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogUtil.getInstance().info(TAG, "doGet()");
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LogUtil.getInstance().info(TAG, "doPost()");
        final var sc = this.getServletConfig();
        charset = sc.getInitParameter("charset");
        request.setAttribute("charset", charset);

        request.setCharacterEncoding(charset);
        response.setContentType("text/html;charset="+charset);

        final var conPath = request.getContextPath();
        final var uri = request.getRequestURI();
        final var url = request.getRequestURL();
        final var command = uri.substring(conPath.length());

        LogUtil.getInstance().info(TAG, "conPath: " + conPath);
        LogUtil.getInstance().info(TAG, "uri: " + uri);
        LogUtil.getInstance().info(TAG, "url: " + url);
        LogUtil.getInstance().info(TAG, "command: " + command);

        //コントローラが多くなるとdbtest20のhandlermappingを使用
        BbsController controller = new BbsController();

        Method[] methods = controller.getClass().getDeclaredMethods();
        Arrays.stream(methods)
                .map(Method::getName)
                .forEach(i -> LogUtil.getInstance().info(TAG, "controller: " + i));

        for(Method method : methods) {
            Annotation annotation = method.getDeclaredAnnotation(RequestMapping.class);
            RequestMapping mapping = (RequestMapping) annotation;

            if(command.equals(mapping.value())) {
                try {
                    Parameter[] params = method.getParameters();
                    Object[] queue = new Object[params.length];

                    for(int i = 0; i < params.length; i++) {
                        Class<?> clazz = params[i].getType();

                        if(clazz == HttpServletRequest.class) {
                            LogUtil.getInstance().info(TAG, "Find request");
                            queue[i] = request;
                            LogUtil.getInstance().info(TAG, "request Successful");
                        } else if(clazz == HttpServletResponse.class) {
                            LogUtil.getInstance().info(TAG, "Find response");
                            queue[i] = response;
                            LogUtil.getInstance().info(TAG, "response Successful");
                        //dto探し
                        } else {
                            LogUtil.getInstance().info(TAG, "Find dto");
                            Constructor<?> constructor = clazz.getConstructor();
                            queue[i] = constructor.newInstance();

                            //setterメソッドを探してパラメータ値を入れる
                            for(Method m : queue[i].getClass().getDeclaredMethods()) {
                                if(m.getName().startsWith("set")) {
                                    LogUtil.getInstance().info(TAG, "dto setter: " + m);
                                    String key = m.getName().replace("set", "").toLowerCase();
                                    String param = request.getParameter(key);
                                    if(param != null) {
                                        m.invoke(queue[i], param);
                                    }
                                }
                            }
                            LogUtil.getInstance().info(TAG, "dto Successful");
                        }
                        LogUtil.getInstance().info(TAG, "size: " + queue.length);
                    }
                    method.invoke(controller, queue);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}