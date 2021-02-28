package cn.tedu.controller;

import cn.tedu.dao.ProductDAO;
import cn.tedu.entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "SendServlet", value = "/send")
public class SendServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //获取参数
        String title=request.getParameter("title");
        String author=request.getParameter("author");
        String intro=request.getParameter("intro");
        String categoryId=request.getParameter("categoryId");
        //获取上传文件
        Part part=request.getPart("file");
        //获取后缀名
        String info=part.getHeader("content-disposition");
        String suffix=info.substring(info.lastIndexOf("."),
                info.length()-1);
        String fileName= UUID.randomUUID()+suffix;
        //得到日期相关路径
        SimpleDateFormat format=
                new SimpleDateFormat("/yyyy/MM/dd/");
        //得到今天日期对象
        Date date=new Date();
        //得到日期路径
        String datePath=format.format(date);//  路径:/年/月/日/
        //得到Tomcat管辖的路径
        String path=
                request.getServletContext().getRealPath("images"+datePath);
        //创建文件夹
        new File(path).mkdirs();
        //存图
        part.write(path+fileName);
        //把参数和路径封装到对象中
        Product product=new Product(0,title,author,intro,
                "images"+datePath+fileName,0,0,
                System.currentTimeMillis(),Integer.parseInt(categoryId));
        ProductDAO dao=new ProductDAO();
        dao.insert(product);
        response.sendRedirect("/home");
    }
}
