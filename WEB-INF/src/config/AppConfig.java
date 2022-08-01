package config;

import java.sql.SQLException;

import dao.BbsRepository;
import dao.BbsRepositoryImpl;
import db.DBConnection;
import db.DBException;
import service.BbsService;
import service.BbsServiceImpl;
import util.LogUtil;

/**
 * AppConfig
 */
public class AppConfig {
    private static final String TAG = "AppConfig";
    private static AppConfig instance;

    private AppConfig() {}
    
    public static AppConfig getInstance() {
        LogUtil.getInstance().info(TAG, "getInstance()");
        if(instance == null) {
            synchronized(AppConfig.class) {
                instance = new AppConfig();
            }
        }
        return instance;
    }

    public BbsService service() {
        LogUtil.getInstance().info(TAG, "service()");
        return new BbsServiceImpl(repository());
    }

    public BbsRepository repository() {
        LogUtil.getInstance().info(TAG, "repository()");
        DBConnection dbc = new DBConnection();
        try {
            dbc.load();
            return new BbsRepositoryImpl(dbc.open());
        } catch (ClassNotFoundException | SQLException e) {
            throw new DBException(e.getMessage());
        }
    }
}
