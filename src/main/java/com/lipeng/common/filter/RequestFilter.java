package com.lipeng.common.filter;

import java.io.IOException;
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
        String params = "";
        Map<String, String[]> paramsMap = request.getParameterMap();
        if (paramsMap != null && !paramsMap.isEmpty()) {
            for (Map.Entry<String, String[]> entry : paramsMap.entrySet()) {
                params += entry.getKey() + ":" + StringUtils
                        .arrayToDelimitedString(entry.getValue(), ",") + ";";
            }
        }
        long start = System.currentTimeMillis();
        try {
            filterChain.doFilter(request, response);
        } catch (Throwable e) {
            throw e;
        } finally {
            long cost = System.currentTimeMillis() - start;
            log.info(String.format("request filter path={}, cost={}, params={}",
                    request.getServletPath(), cost, params));
        }
    }

    @Override
    public void destroy() {

    }

}