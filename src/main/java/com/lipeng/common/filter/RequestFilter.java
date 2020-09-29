package com.lipeng.common.filter;

import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;

@Slf4j
public class RequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        String params = "";
        Map<String, String[]> paramsMap = request.getParameterMap();
        if (paramsMap != null && !paramsMap.isEmpty()) {
            for (Map.Entry<String, String[]> entry : paramsMap.entrySet()) {
                params += entry.getKey() + ":" + StringUtils
                        .arrayToDelimitedString(entry.getValue(), ",") + ";";
            }
        }
        if (HttpMethod.POST.name().equals(request.getMethod()) && StringUtils.isEmpty(params)) {
            request = new RepeatedlyReadRequestWrapper(request);
            String body = IOUtils.toString(request.getInputStream(), "UTF-8");
            paramsMap = JSON.toJavaObject(JSON.parseObject(body), Map.class);
            for (Map.Entry<String, String[]> entry : paramsMap.entrySet()) {
                params += entry.getKey() + ":" + entry.getValue() + ";";
            }
        }
        long start = System.currentTimeMillis();
        try {
            filterChain.doFilter(request, response);
        } finally {
            long cost = System.currentTimeMillis() - start;
            log.info("request filter path={}, cost={}ms, params={}",
                    new String[]{request.getServletPath(), String.valueOf(cost), params});
            log.info(String.format("request filter path=%s, cost=%sms, params=%s",
                    request.getServletPath(), cost, params));
            log.info(MessageFormat.format("request filter path={0}, cost={1}ms, params={2}",
                    request.getServletPath(), cost, params));
        }
    }

    @Override
    public void destroy() {

    }

}