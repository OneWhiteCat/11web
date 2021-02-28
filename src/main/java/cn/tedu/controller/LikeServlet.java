package cn.tedu.controller;

import cn.tedu.dao.ProductDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LikeServlet", value = "/like")
public class LikeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        //获取Session
        HttpSession session=request.getSession();
        //获取Session保存的作品信息
        String likeId=(String)session.getAttribute("like"+id);
        //如果likeId为null
        if(likeId==null){
            ProductDAO dao=new ProductDAO();
            dao.LikeById(id);
            //把点过赞的id保存进Session
            session.setAttribute("like"+id,id);
        }
        response.sendRedirect("/detail?id="+Integer.parseInt(id));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
