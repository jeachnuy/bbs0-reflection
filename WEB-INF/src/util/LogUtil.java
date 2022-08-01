package util;
/**
 * LogUtil
 */
public final class LogUtil {
    private static final LogUtil instance = new LogUtil();

    private LogUtil() {}

    public static LogUtil getInstance() {
        return instance;
    }

    public void info(String TAG, String msg) {
        System.out.println("=".repeat(20) + "INFO " + TAG + "." + msg);
    }
}