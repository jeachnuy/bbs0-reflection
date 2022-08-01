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
                out.println(data+"のデータを削除しました。<br><br>");
                out.println("<a href=\"select\">全データの表示</a><br>");
                out.println("<a href=\"index.html\">戻る</a>");
                out.flush();
            } else {
                out.println("1件のデータを追加しました。<br><br>");
                out.println("<a href=\"select\">全データの表示</a><br>");
                out.println("<a href=\"index.html\">戻る</a>");
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
            out.println("<h1>サーバーが見つかりません。</h1>");
            out.flush();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
