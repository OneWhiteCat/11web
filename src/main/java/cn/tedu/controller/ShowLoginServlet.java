package cn.tedu.controller;

import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ShowLoginServlet", value = "/showlogin")
public class ShowLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Context context=new Context();
        //取出Cookie的用户名和密码
        Cookie[] cookies=request.getCookies();
        //
        if(cookies!=null){
            for(Cookie c:cookies){
                String name=c.getName();
                String value=c.getValue();
                if(name.equals("username")){
                    context.setVariable("username",value);
                }else if(name.equals("password")){
                    context.setVariable("password",value);
                }
            }
        }
        ThUtils.print("login.html",context,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
