package com.lipeng.common.udp.listener;

import com.lipeng.common.udp.manager.UdpManager;
import com.lipeng.common.udp.manager.UdpReceiveThread;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: lipeng 910138
 * @Date: 2020/10/14 15:24
 */
@Slf4j
public class UdpStartListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UdpManager.getInstance();
        new UdpReceiveThread().start();
        log.info("UdpStartListener contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}