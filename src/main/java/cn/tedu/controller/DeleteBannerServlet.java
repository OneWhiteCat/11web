package cn.tedu.controller;

import cn.tedu.dao.BannerDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "DeleteBannerServlet", value = "/deletebanner")
public class DeleteBannerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        BannerDAO dao=new BannerDAO();
        String url=dao.findUrlById(id);
        String path=request.getServletContext().getRealPath(url);
        new File(path).delete();
        dao.deleteById(id);
        response.sendRedirect("/showbanner");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
