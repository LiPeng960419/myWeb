package com.lipeng.common.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @Author: lipeng 910138
 * @Date: 2020/8/29 22:11
 */
@Slf4j
public class RequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        log.info("RequestListener requestDestroyed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        log.info("RequestListener requestInitialized");
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        log.info("getRequestURI:{}", request.getRequestURI());
        log.info("getRequestURL:{}", request.getRequestURL().toString());
        // 如果tomcat没有设置-Dfile.encoding=UTF-8
        // 那么这里获取的会乱码  可以在前段encode在后端decode
        String decode = "";
        if (StringUtils.isNotBlank(request.getQueryString())) {
            try {
                decode = URLDecoder.decode(request.getQueryString(), StandardCharsets.UTF_8.name());
            } catch (UnsupportedEncodingException e) {
                log.error("decode:{} error", request.getQueryString(), e);
            }
            log.info("getQueryString:{}", decode);
        }
        log.info("getMethod:{}", request.getMethod());
        if ("GET".equals(request.getMethod())) {
            log.info("full request url:{}", request.getRequestURL().toString() + "?" + decode);
        }
    }

}