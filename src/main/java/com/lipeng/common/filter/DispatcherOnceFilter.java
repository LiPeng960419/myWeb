package com.lipeng.common.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @Author: lipeng 910138
 * @Date: 2020/10/12 16:35
 */
@Slf4j
public class DispatcherOnceFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("DispatcherOnceFilter");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}