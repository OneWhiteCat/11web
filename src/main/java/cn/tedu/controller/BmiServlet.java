package cn.tedu.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BmiServlet", value = "/bmi")
public class BmiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String h=request.getParameter("h");
        String w=request.getParameter("w");
        float height=Float.parseFloat(h);
        float weight=Float.parseFloat(w);
        float bmi=weight/(height*height);
        String info=null;
        if (bmi<18.5){
            info="太瘦了";
        }else if(bmi<=24){
            info="正常";
        }else  if(bmi<=28){
            info="微胖";
        }else {
            info="肥胖";
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw= response.getWriter();
        pw.print(info);
        pw.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
