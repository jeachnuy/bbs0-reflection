package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBException;
import model.Bbs;
import util.LogUtil;

/**
 * BbsRepositoryImpl
 */
public class BbsRepositoryImpl implements BbsRepository {
    private static final String TAG = "BbsRepositoryImpl";
    private final Connection cn;

    public BbsRepositoryImpl(Connection connection) {
        this.cn = connection;
    }

    @Override
    public List<Bbs> findAll() {
        LogUtil.getInstance().info(TAG, "findAll()");
        //ì˙ïtÇÃâ∫ÇËèá
        final var sql = "select * from bbs order by time asc";
        try (Statement st = cn.createStatement(); 
            ResultSet rs = st.executeQuery(sql)) {
            List<Bbs> list = new ArrayList<>();
            while (rs.next()) {
                list.add(Bbs.from(rs));
            }
            LogUtil.getInstance().info(TAG, "Find All Successful");
            return list;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        LogUtil.getInstance().info(TAG, "deleteAll()");
        final var sql = "delete from bbs";
        try (Statement st = cn.createStatement(); 
            ResultSet rs = st.executeQuery(sql)) {
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        LogUtil.getInstance().info(TAG, "Delete All Successful");
    }

    @Override
    public Bbs save(Bbs t) {
        LogUtil.getInstance().info(TAG, "save()");
        final var sql = "insert into bbs values(?, ?, ?)";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            LogUtil.getInstance().info(TAG, "Name: " + t.getName());
            LogUtil.getInstance().info(TAG, "Time: " + t.getTime().toString());
            LogUtil.getInstance().info(TAG, "Text: " + t.getText());

            ps.setString(1, t.getName());
            ps.setString(2, t.getTime().toString());
            ps.setString(3, t.getText());
            ps.executeUpdate();
            LogUtil.getInstance().info(TAG, "Save Successful");
            return t;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

}