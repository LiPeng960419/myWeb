package com.lipeng.common.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

/**
 * @Author: lipeng 910138
 * @Date: 2019/9/11 0:51
 */
@Slf4j
public class RepeatedlyReadRequestWrapper extends HttpServletRequestWrapper {

    private final byte[] bytes;

    public RepeatedlyReadRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        bytes = IOUtils.toByteArray(request.getInputStream());
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        return new ServletInputStream() {
            @Override
            public int read() {
                return bais.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() {
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        BufferedReader temp = new BufferedReader(new InputStreamReader(is));
        return temp;
    }

}