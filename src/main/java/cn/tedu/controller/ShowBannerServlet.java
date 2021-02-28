package cn.tedu.controller;

import cn.tedu.dao.BannerDAO;
import cn.tedu.entity.Banner;
import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ShowBannerServlet", value = "/showbanner")
public class ShowBannerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BannerDAO dao=new BannerDAO();
        List<Banner> list=dao.findall();
        //查询到的内容在页面中显示
        Context context=new Context();
        context.setVariable("list",list);
        ThUtils.print("banner.html",context,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
