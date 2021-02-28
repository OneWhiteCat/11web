package cn.tedu.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HelloServlet", value = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String info=request.getParameter("info");
        String msg=request.getParameter("msg");

        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw= response.getWriter();
        pw.print("我是服务器接收到了数据:"+info+msg);
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
