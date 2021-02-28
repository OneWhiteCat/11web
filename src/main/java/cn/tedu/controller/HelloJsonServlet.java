package cn.tedu.controller;

import cn.tedu.dao.ProductDAO;
import cn.tedu.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "HelloJsonServlet", value = "/hellojson")
public class HelloJsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询所有作品
        ProductDAO dao=new ProductDAO();
        List<Product> list = dao.findAll();
        //把list装入json
        ObjectMapper om=new ObjectMapper();
        String jsonStr=om.writeValueAsString(list);
        System.out.println(jsonStr);
        //发送到页面
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw=response.getWriter();
        pw.print(jsonStr);
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
