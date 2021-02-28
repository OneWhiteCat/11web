package cn.tedu.filter;

import cn.tedu.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "MyFilter",
        urlPatterns = {"/showsend", "/showbanner","/delete",
                "/deletebanner"})

public class MyFilter implements Filter {
    //过滤器初始化时
    public void init(FilterConfig config) throws ServletException {
    }
    //过滤器销毁
    public void destroy() {
    }
    //接收到请求
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //将父类ServletRequest强转为子类
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) resp;
        //取出Session中的user
        User user=(User)request.getSession().getAttribute("user");
        if(user==null){
            response.sendRedirect("/showlogin");
        }else {
            chain.doFilter(req, resp);
        }
    }
}
