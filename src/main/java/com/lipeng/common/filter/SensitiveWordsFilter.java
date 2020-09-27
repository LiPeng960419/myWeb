package com.lipeng.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: lipeng 910138
 * @Date: 2020/9/28 0:48
 */
@Slf4j
public class SensitiveWordsFilter implements Filter {

    private final Set<String> set = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) {
        ServletContext servletContext = filterConfig.getServletContext();
        String filePath = servletContext.getRealPath("/WEB-INF/classes/sensitivewords.txt");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                set.add(line);
            }
        } catch (Exception e) {
            log.error("SensitiveWordsFilter init error", e);
        } finally {
            IOUtils.closeQuietly(bufferedReader);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletRequest proxy = (ServletRequest) Proxy.newProxyInstance(request.getClass().getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if ("getParameter".equals(method.getName())) {
                    String value = (String) method.invoke(request, args);
                    if (StringUtils.isNotEmpty(value)) {
                        for (String s : set) {
                            if (value.contains(s)) {
                                return value.replace(s, "***");
                            }
                        }
                    }
                }
                return method.invoke(request, args);
            }
        });
        chain.doFilter(proxy, response);
    }

    @Override
    public void destroy() {

    }

}