package com.lipeng.common.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: lipeng 910138
 * @Date: 2020/8/29 18:04
 */
@Slf4j
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("MyServletContextListener contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("MyServletContextListener contextDestroyed");
    }
}
