package com.lipeng.common.tcp.listener;

import com.lipeng.common.tcp.manager.TcpManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: lipeng 910138
 * @Date: 2020/10/14 15:24
 */
@Slf4j
public class TcpStartListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        TcpManager.getInstance();
        log.info("TcpStartListener contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        TcpManager.stopSocket();
        log.info("TcpStartListener stopSocket");
    }

}