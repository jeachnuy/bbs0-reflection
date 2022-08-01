package db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import util.LogUtil;

/**
 * DBConnection
 */
public class DBConnection {
    private static final String TAG = "DBConnection";
    private static String CLASSNAME;
    private static String JDBC_URL;
    private static String USERNAME;
    private static String PASSWORD;

    public DBConnection() {
        LogUtil.getInstance().info(TAG, "ctor()");
        InputStream inputStream;
        final var resource = "db.properties";
        final var properties = new Properties();

        try {
            inputStream = this.getClass().getResourceAsStream(resource);
            properties.load(inputStream);

            CLASSNAME = properties.getProperty("dataSourceClassName");
            JDBC_URL = properties.getProperty("jdbcUrl");
            USERNAME = properties.getProperty("dataSource.user");
            PASSWORD = properties.getProperty("dataSource.password");

            LogUtil.getInstance().info(TAG, "CLASSNAME: " +  CLASSNAME);
            LogUtil.getInstance().info(TAG, "JDBC_URL: " +  JDBC_URL);
            LogUtil.getInstance().info(TAG, "USERNAME: " +  USERNAME);
            LogUtil.getInstance().info(TAG, "PASSWORD: " +  PASSWORD);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void load() throws ClassNotFoundException {
        LogUtil.getInstance().info(TAG, "load()");
        Class.forName(CLASSNAME);
    }

    public Connection open() throws SQLException {
        LogUtil.getInstance().info(TAG, "open()");
        Connection cn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        LogUtil.getInstance().info(TAG, "データベースに接続しました。");

        return cn;
    }
    
    public void close(Connection cn, PreparedStatement ps, ResultSet rs) {
        LogUtil.getInstance().info(TAG, "close()");
        if(rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            close(cn,ps);
        }
    }

    public void close(Connection cn, PreparedStatement ps) {
        LogUtil.getInstance().info(TAG, "close()");
        if(ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            close(cn);
        }
    }

    public void close(Connection cn) {
        LogUtil.getInstance().info(TAG, "close()");
        if(cn != null) {
            try {
                cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            LogUtil.getInstance().info(TAG, "データベースから切断しました。");
        }
    }
}
