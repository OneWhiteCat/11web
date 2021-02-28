package cn.tedu.controller;

import cn.tedu.dao.UserDAO;
import cn.tedu.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/loginaction")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String username=request.getParameter("username");
        String password=request.getParameter("password");

        UserDAO dao=new UserDAO();
        User user=dao.login(username,password);
        //判断登陆成功
        if(user!=null){
            //判断是否记住用户名和密码
            String rem=request.getParameter("rem");
            if(rem!=null){//需要记住用户名和密码
                //创建Cookie
                Cookie c1=new Cookie("username",username);
                Cookie c2=new Cookie("password",password);
                //下发到浏览器
                response.addCookie(c1);
                response.addCookie(c2);
            }
            //通过Session记住登录
            //getSession()方法 如果通过Session找到了曾经创建的Session
            //会直接返回，如果没找到则会新建
            HttpSession session=request.getSession();
            //把user对象装进去
            session.setAttribute("user",user);
            //重定向首页
            response.sendRedirect("/home");
        }else {//
            //重定向登录页面
            response.sendRedirect("/showlogin");
        }

    }
}
