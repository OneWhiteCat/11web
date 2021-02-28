package cn.tedu.controller;

import cn.tedu.dao.ProductDAO;
import cn.tedu.entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "DeleteServlet", value = "/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        ProductDAO dao=new ProductDAO();
        Product p=dao.findById(id);
        String url=p.getUrl();
        String path=request.getServletContext().getRealPath(url);
        //D:\Idea_workspace\11vrd\target\11vrd\
        // images\2021\02\19\5ba71ba2-a646-4529-959e-9b300d30edcf.png
        new File(path).delete();
        dao.deleteById(id);
        response.sendRedirect("/home");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
