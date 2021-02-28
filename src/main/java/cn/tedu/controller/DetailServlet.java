package cn.tedu.controller;

import cn.tedu.dao.CategoryDAO;
import cn.tedu.dao.ProductDAO;
import cn.tedu.entity.Category;
import cn.tedu.entity.Product;
import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DetailServlet", value = "/detail")
public class DetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        ProductDAO dao=new ProductDAO();
        //浏览次数加一
        HttpSession session=request.getSession();
        String viewId=(String) session.getAttribute("view"+id);
        if(viewId==null) {
            dao.viewById(id);
            session.setAttribute("view"+id,id);
        }
        //通过id 查询出此id对应的作品
        Product p=dao.findById(id);
        Context context=new Context();
        //装进容器
        context.setVariable("product",p);
        //分类
        CategoryDAO cDao=new CategoryDAO();
        List<Category> list=cDao.findall();
        context.setVariable("list",list);
        //浏览最多
        context.setVariable("vList",dao.findViewList());
        //最受欢迎
        context.setVariable("lList",dao.findLikeList());
        //登录用户
        context.setVariable("user",request.getSession()
        .getAttribute("user"));
        ThUtils.print("detail.html",context,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
