package com.lipeng.common.tcp.manager;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

/**
 * @Author: lipeng 910138
 * @Date: 2020/10/14 15:25
 */
@Slf4j
public class TcpManager {

    private static TcpReceiveThread receiveThread;

    private static final String IP = "10.9.212.61";

    private static final int PORT = 9000;

    private static TcpManager instance;

    private static ServerSocket serverSocket;

    private static final String DEFAULT_CHARSET = "UTF-8";

    private static InetAddress address;

    private TcpManager() {
        if (isClosed()) {
            try {
                address = InetAddress.getByName(IP);
                serverSocket = new ServerSocket(PORT, 10, address);
                if (receiveThread == null || !receiveThread.isRunning()) {
                    receiveThread = new TcpReceiveThread();
                    receiveThread.start();
                }
            } catch (Exception e) {
                log.error("init serverSocket error", e);
            }
        }
    }

    public static synchronized void stopSocket() {
        receiveThread.stop();
        try {
            serverSocket.close();
        } catch (IOException e) {
            log.error("socket close error", e);
        }
    }

    public synchronized boolean isClosed() {
        return (serverSocket == null) ? true : false;
    }

    public synchronized static TcpManager getInstance() {
        if (instance == null) {
            instance = new TcpManager();
        }
        return instance;
    }

    public static void sendData(String data) {
        sendData(data, DEFAULT_CHARSET);
    }

    public static void sendData(String data, String charset) {
        if (data == null || serverSocket == null) {
            return;
        }
        log.info("[Send]:" + data);
        OutputStream os = null;
        Socket socket = null;
        DataOutputStream dataOutputStream = null;
        try {
            socket = new Socket(address, PORT);
            os = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(os);
            dataOutputStream.write(data.getBytes(charset));
            dataOutputStream.flush();
            socket.shutdownOutput();//关闭输出流
        } catch (Exception e) {
            log.error("sendData error", e);
        } finally {
            IOUtils.closeQuietly(dataOutputStream);
            IOUtils.closeQuietly(os);
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    log.error("socket close error", e);
                }
            }
        }
    }

    public static String receiveData() {
        return receiveData(DEFAULT_CHARSET);
    }

    public static String receiveData(String charset) {
        if (serverSocket == null) {
            return null;
        }
        String data = null;
        BufferedReader br = null;
        try {
            Socket receiveSocket = serverSocket.accept();
            br = new BufferedReader(new InputStreamReader(receiveSocket.getInputStream(), charset));
            StringBuilder builder = new StringBuilder();
            while ((data = br.readLine()) != null) {
                builder.append(data);
            }
            data = builder.toString();
            log.info("[Receviced]:" + data);
        } catch (Exception e) {
            log.error("receiveData error", e);
        } finally {
            IOUtils.closeQuietly(br);
        }
        return data;
    }

}