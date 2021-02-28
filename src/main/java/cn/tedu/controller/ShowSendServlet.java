package cn.tedu.controller;

import cn.tedu.dao.CategoryDAO;
import cn.tedu.entity.Category;
import cn.tedu.entity.User;
import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowSendServlet", value = "/showsend")
public class ShowSendServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取出Session对象
        User user=(User)request.getSession().getAttribute("user");
        if(user==null){
            response.sendRedirect("/showlogin");
            return;
        }
        //查询所有分类
        CategoryDAO dao=new CategoryDAO();
        List<Category> list=dao.findall();
        //把查询到的所有分类信息装进容器中
        Context context=new Context();
        context.setVariable("list",list);
        ThUtils.print("send.html",context,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
