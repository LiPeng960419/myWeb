package com.lipeng.common.udp.manager;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: lipeng 910138
 * @Date: 2020/10/14 15:25
 */
@Slf4j
public class UdpManager {

    private static UdpReceiveThread receiveThread;

    private static final String IP = "10.9.212.61";

    private static final int PORT = 8000;

    private static UdpManager instance;

    private static DatagramSocket socket;

    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final int RECEIVE_BUFFER_SIZE = 1024 * 100;

    private static InetSocketAddress address;

    private UdpManager() {
        if (isClosed()) {
            try {
                address = new InetSocketAddress(InetAddress.getByName(IP), PORT);
                socket = new DatagramSocket(address);
                socket.setReceiveBufferSize(RECEIVE_BUFFER_SIZE);
                if (receiveThread == null || !receiveThread.isRunning()) {
                    receiveThread = new UdpReceiveThread();
                    receiveThread.start();
                }
            } catch (Exception e) {
                log.error("init socket error", e);
            }
        }
    }

    public static synchronized void stopSocket() {
        receiveThread.stop();
        socket.close();
    }

    public synchronized boolean isClosed() {
        return (socket == null) ? true : false;
    }

    public synchronized static UdpManager getInstance() {
        if (instance == null) {
            instance = new UdpManager();
        }
        return instance;
    }

    public static void sendData(String data) {
        sendData(data, DEFAULT_CHARSET);
    }

    public static void sendData(String data, String charset) {
        if (data == null || socket == null) {
            return;
        }
        log.info("[Send]:" + data);
        DatagramPacket dp = null;
        try {
            byte[] buf = (charset == null) ? data.getBytes()
                    : data.getBytes(charset);
            dp = new DatagramPacket(buf, buf.length, address);
            socket.send(dp);
        } catch (Exception e) {
            log.error("sendData error", e);
        }
    }

    public static String receiveData() {
        return receiveData(DEFAULT_CHARSET);
    }

    public static String receiveData(String charset) {
        if (socket == null) {
            return null;
        }
        DatagramPacket dp = null;
        String data = null;
        try {
            byte[] buf = new byte[RECEIVE_BUFFER_SIZE];
            dp = new DatagramPacket(buf, buf.length);
            socket.receive(dp);
            data = (charset == null) ? new String(dp.getData(), 0,
                    dp.getLength()) : new String(dp.getData(), 0,
                    dp.getLength(), charset);
            log.info("[Receviced]:" + data);
        } catch (Exception e) {
            log.error("receiveData error", e);
        }
        return data;
    }

}