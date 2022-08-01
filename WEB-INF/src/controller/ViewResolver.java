package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.LogUtil;

/**
 * ViewResolver
 */
public class ViewResolver {
    private static final String TAG = "ViewResolver";

    public static void resolve(String path, HttpServletRequest request, HttpServletResponse response) {
        LogUtil.getInstance().info(TAG, "resolve()");
        try {
            final var dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        } catch(Exception e) {
            writeErr(response);
        }
    }

    public static void writeErr(HttpServletResponse response) {
        LogUtil.getInstance().info(TAG, "writeErr()");
        try {
            response.setContentType("text/html;charset=utf-8");
            final var out = response.getWriter();
            out.println("<h1>サーバーが見つかりません。</h1>");
            out.flush();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}