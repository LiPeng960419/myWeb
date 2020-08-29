package com.lipeng.common.servlet;

import com.lipeng.service.UserService;
import com.lipeng.utils.SpringContextUtils;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @Author: lipeng 910138
 * @Date: 2020/8/29 17:39
 */
@Slf4j
public class SpringContextListener extends ContextLoaderListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        log.info("SpringContextListener加载开始。");
        ServletContext servletContext = event.getServletContext();
        super.contextInitialized(event);
        ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        SpringContextUtils.setApplicationContext(ac);
        UserService userService = SpringContextUtils.getBean(UserService.class);
        log.info("listUser:{}", userService.listUser().toString());
        log.info("SpringContextListener加载结束。");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        super.contextDestroyed(event);
        log.info("SpringContextListener销毁。");
    }

}