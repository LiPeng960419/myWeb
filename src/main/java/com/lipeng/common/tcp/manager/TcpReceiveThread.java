package com.lipeng.common.tcp.manager;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: lipeng 910138
 * @Date: 2020/10/14 15:52
 */
@Slf4j
public class TcpReceiveThread implements Runnable {

    private volatile boolean running;

    private Thread t;

    public void stop() {
        running = false;
    }

    public void shutdown() {
        t.interrupt();
    }

    public boolean isRunning() {
        return running;
    }

    public void start() {
        running = true;
        t = new Thread(this);
        t.setName("TcpReceiveThread");
        t.start();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted() && running) {
            String data = TcpManager.receiveData();
            if (data != null) {
                log.info("TcpManager.receiveData:{}", data);
            }
        }
    }

}