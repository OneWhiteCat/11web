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

@WebServlet(name = "LoadMoreServlet", value = "/loadmore")
public class LoadMoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String count=request.getParameter("count");

        ProductDAO dao=new ProductDAO();
        List<Product> list=dao.loadMore(count);
        //作品集合装进json
        ObjectMapper om=new ObjectMapper();
        String jsonStr=om.writeValueAsString(list);
        //把得到的数据返回给客户端
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw= response.getWriter();
        pw.print(jsonStr);
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
