package model;
/**
 * Bbs
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import dto.BbsDto;
import util.LogUtil;

public class Bbs {
    private static final String TAG = "Bbs";
    private String name;
    private Timestamp time;
    private String text;
    
    public Bbs() {}

    public Bbs(String name, Timestamp time, String text) {
        LogUtil.getInstance().info(TAG, "ctor(): obj");
        this.name = name;
        this.time = time;
        this.text = text;
    }

    public Bbs(BbsDto dto) {
        LogUtil.getInstance().info(TAG, "ctor(): dto");
        this.name = dto.getName();
        this.time = dto.getTime();
        this.text = dto.getText();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getTime() {
        return time;
    }
    
    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static Bbs from(ResultSet rs) throws SQLException {
        LogUtil.getInstance().info(TAG, "from()");
        var bbs = new Bbs();
        bbs.setName(rs.getString("NAME"));
        bbs.setTime(rs.getTimestamp("TIME"));
        bbs.setText(rs.getString("TEXT"));

        return bbs;
    }
}