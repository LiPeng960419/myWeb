package com.lipeng.common.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: lipeng 910138
 * @Date: 2020/8/29 21:59
 */
@Slf4j
public class TimerStarterListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                log.info(Thread.currentThread().getName() + " exec task");
            }
        };
        timer.schedule(task, 0, 10000);
        log.info("TimerStarterListener contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("TimerStarterListener contextDestroyed");
    }

}