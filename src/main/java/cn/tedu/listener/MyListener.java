package cn.tedu.listener;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebListener
public class MyListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public MyListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* 工程部署到tomcat中运行时 执行此方法 */

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* 工程停止运行时执行 */

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. (Session 创建)*/

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. (Session 销毁)*/

    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
    /* 属性添加*/

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    /*属性删除*/

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    /*属性替换*/

    }
}
