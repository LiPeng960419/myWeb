package com.lipeng.common.listener;

import com.lipeng.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * @Author: lipeng 910138
 * @Date: 2020/8/29 21:24
 */
@Slf4j
public class WebContextLoaderListener extends ContextLoaderListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        // spring上下文的加载
        super.contextInitialized(event);
        ServletContext servletContext = event.getServletContext();
        WebApplicationContext requiredWebApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        SpringContextUtils.setApplicationContext(requiredWebApplicationContext);
        log.info("WebContextLoaderListener contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        super.contextDestroyed(event);
        log.info("WebContextLoaderListener contextDestroyed");
    }

}