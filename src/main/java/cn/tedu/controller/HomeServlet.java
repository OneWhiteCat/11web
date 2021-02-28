package cn.tedu.controller;

import cn.tedu.dao.BannerDAO;
import cn.tedu.dao.CategoryDAO;
import cn.tedu.dao.ProductDAO;
import cn.tedu.entity.Banner;
import cn.tedu.entity.Category;
import cn.tedu.entity.Product;
import cn.tedu.entity.User;
import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取cid
        String cid=request.getParameter("cid");
        //获取keyword
        String keyword=request.getParameter("keyword");

        Context context=new Context();
        //创建BannerDAO并查询所有Banner
        BannerDAO banner=new BannerDAO();
        List<Banner> bannerList=banner.findall();
        context.setVariable("bannerList",bannerList);
        //创建CategoryDAO并调用查询所有分类的方法
        CategoryDAO dao=new CategoryDAO();
        List<Category> list=dao.findall();
        context.setVariable("list",list);
        //取出Session的对象
        User user=(User)request.getSession().getAttribute("user");
        //添加到页面
        context.setVariable("user",user);
        //查询所有作品
        ProductDAO pDao = new ProductDAO();
        if(cid!=null){//有参数//查询分类作品
            List<Product> plist = pDao.findBycid(cid);
            context.setVariable("plist", plist);
        }else if(keyword!=null){
            List<Product> plist=pDao.findByKeyword(keyword);
            context.setVariable("plist",plist);
        }else {//无参数
            List<Product> plist = pDao.findAll();
            context.setVariable("plist", plist);
        }
        //查询浏览最多
        List<Product> vList=pDao.findViewList();
        context.setVariable("vList",vList);
        //查询最受欢迎
        List<Product> lList=pDao.findLikeList();
        context.setVariable("lList",lList);

        ThUtils.print("/home.html",context,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
