package com.lipeng.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: lipeng 910138
 * @Date: 2020/10/12 16:08
 */
@Slf4j
public class DispatcherFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /*
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
    */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("DispatcherFilter doFilter");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}