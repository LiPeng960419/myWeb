package com.lipeng.common.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.io.IOUtils;

/**
 * @Author: lipeng 910138
 * @Date: 2020/9/28 11:07
 */
public class SensitiveWordsJsonWrapper extends HttpServletRequestWrapper {

    private byte[] body; //用于保存读取body中数据

    public SensitiveWordsJsonWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = IOUtils.toByteArray(request.getInputStream());
    }

    /**
     * 获取body中的数据
     *
     * @return
     */
    public byte[] getBody() {
        return body;
    }

    /**
     * 把处理后的参数放到body里面
     *
     * @param body
     */
    public void setBody(byte[] body) {
        this.body = body;
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public int read() {
                return bais.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

}