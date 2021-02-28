package cn.tedu.controller;

import cn.tedu.dao.BannerDAO;
import cn.tedu.entity.Banner;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "AddBannnerServlet", value = "/addbanner")
public class AddBannnerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Part p=request.getPart("file");
        String fileName=p.getSubmittedFileName();
        String suffix=fileName.substring(fileName.lastIndexOf("."));
        String uuid=UUID.randomUUID()+suffix;
        String path=request.getServletContext().getRealPath("images/"+uuid);
        p.write(path);
        Banner banner=new Banner(0,"images/"+uuid);
        //调用insert方法
        BannerDAO dao=new BannerDAO();
        dao.insert(banner);
        response.sendRedirect("/showbanner");
    }
}
