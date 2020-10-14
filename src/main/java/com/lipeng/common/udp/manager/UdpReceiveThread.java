package com.lipeng.common.udp.manager;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: lipeng 910138
 * @Date: 2020/10/14 15:52
 */
@Slf4j
public class UdpReceiveThread implements Runnable {

    private volatile boolean running;

    public void stop() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public void start() {
        running = true;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while (running) {
            String data = UdpManager.receiveData();
            if (data != null) {
                log.info("UdpManager.receiveData:{}", data);
            }
        }
    }

}