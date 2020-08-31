package com.lipeng.common.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Date;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: lipeng 910138
 * @Date: 2020/8/29 22:39
 */
@Slf4j
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("sessionCreated:{},time:{}", se.getSession().getId() + "," + se.getSession().getMaxInactiveInterval(), new Date());
        Enumeration<Object> attributeNames = se.getSession().getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            log.info("attr:{}", attributeNames.nextElement());
        }
        // invalidate触发sessionDestroyed
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                se.getSession().invalidate();
            }
        };
        timer.schedule(task, 5000);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("sessionDestroyed:{},time:{}", se.getSession().getId() + "," + se.getSession().getMaxInactiveInterval(), new Date());
    }

}