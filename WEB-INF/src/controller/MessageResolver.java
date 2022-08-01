package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import util.LogUtil;

/**
 * MessageResolver
 */
public class MessageResolver {
    private static final String TAG = "MessageResolver";

    public static void resolve(Object data, HttpServletResponse response) {
        LogUtil.getInstance().info(TAG, "resolve()");
        try {
            response.setContentType("text/html;charset=utf-8");
            final var out = response.getWriter();
            if(data.getClass() == Integer.class) {
                out.println(data+"�̃f�[�^���폜���܂����B<br><br>");
                out.println("<a href=\"select\">�S�f�[�^�̕\��</a><br>");
                out.println("<a href=\"index.html\">�߂�</a>");
                out.flush();
            } else {
                out.println("1���̃f�[�^��ǉ����܂����B<br><br>");
                out.println("<a href=\"select\">�S�f�[�^�̕\��</a><br>");
                out.println("<a href=\"index.html\">�߂�</a>");
                out.flush();
            }
        } catch(Exception e) {
            writeErr(response);
        }
    }

    public static void writeErr(HttpServletResponse response) {
        LogUtil.getInstance().info(TAG, "writeErr()");
        try {
            response.setContentType("text/html;charset=utf-8");
            final var out = response.getWriter();
            out.println("<h1>�T�[�o�[��������܂���B</h1>");
            out.flush();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
