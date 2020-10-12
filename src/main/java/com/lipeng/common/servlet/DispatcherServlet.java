package com.lipeng.common.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: lipeng 910138
 * @Date: 2020/10/12 16:11
 */
public class DispatcherServlet extends HttpServlet {

    private static final long serialVersionUID = -1998106927661902186L;

    /*
    针对filter
    到了servlet-2.4中Filter默认下只拦截外部提交的请求，
    forward和include这些内部转发都不会被过滤，
    但是有时候我们需要 forward的时候也用到Filter。
    配置如下
    <filter>
        <filter-name>TestFilter</filtername>
        <filter-class>anni.TestFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>TestFilter</filtername>
        <url-pattern>/*</url-pattern>
        <dispatcher>REQUEST</dispatcher>
        <dispatcher>FORWARD</dispatcher>
        <dispatcher>INCLUDE</dispatcher>
        <dispatcher>EXCEPTION</dispatcher>
    </filter-mapping>
    配置后redirect,forward的请求会经过过滤器

    针对OncePerRequestFilter
    redirect,forward的请求会经过过滤器
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // forward 前后页共用一个request，可以通过此来传递一些数据或者session信息
        // URL中所包含的“/”表示应用程序(项目)的路径 地址栏不变 速度要快
        // req.getRequestDispatcher("/index.jsp").forward(req, resp);
        // req.getRequestDispatcher("/firstServlet").forward(req, resp);

        // redirect前后页不共用一个request，不能读取转向前通过request.setAttribute()设置的属性值
        // URL种所包含的"/"表示根目录的路径 地址栏发生改变 转向的速度相对要慢
        String contextPath = req.getContextPath();
        // resp.sendRedirect(contextPath + "/index.jsp");
        resp.sendRedirect(contextPath + "/firstServlet");
    }

}