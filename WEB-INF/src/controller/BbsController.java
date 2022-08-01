package controller;
/**
 * BbsController
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.AppConfig;
import dto.BbsDto;
import service.BbsService;
import util.LogUtil;
import util.RequestMapping;

public class BbsController {
    private static final String TAG = "BbsController";
    private final BbsService service = AppConfig.getInstance().service();
    
    @RequestMapping("/insert")
    public void insert(BbsDto dto, HttpServletRequest request, HttpServletResponse response) {
        LogUtil.getInstance().info(TAG, "insert()");
        LogUtil.getInstance().info(TAG, dto.toString());
        service.insert(dto);
        MessageResolver.resolve("", response);
    }

    @RequestMapping("/select")
    public void select(HttpServletRequest request, HttpServletResponse response) {
        LogUtil.getInstance().info(TAG, "select()");
        request.setAttribute("bbss", service.select());
        ViewResolver.resolve("select.jsp", request, response);
    }

    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        LogUtil.getInstance().info(TAG, "delete()");
        int count = service.delete();
        MessageResolver.resolve(count, response);
    }
}
