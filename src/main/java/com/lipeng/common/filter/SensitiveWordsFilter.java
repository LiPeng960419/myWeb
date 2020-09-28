package com.lipeng.common.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

/**
 * @Author: lipeng 910138
 * @Date: 2020/9/28 0:48
 */
@Slf4j
public class SensitiveWordsFilter implements Filter {

    private final Set<String> set = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) {
        SenstiveWordsUtils.init(filterConfig, set);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        ServletRequest proxy = (ServletRequest) Proxy
                .newProxyInstance(request.getClass().getClassLoader(),
                        request.getClass().getInterfaces(), new InvocationHandler() {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args)
                                    throws Throwable {
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
                                if ("getParameterValues".equals(method.getName())) {
                                    String[] values = (String[]) method.invoke(request, args);
                                    String[] newValues = new String[values.length];
                                    for (int i = 0; i < values.length; i++) {
                                        if (StringUtils.isNotEmpty(values[i])) {
                                            for (String s : set) {
                                                if (values[i].contains(s)) {
                                                    newValues[i] = values[i].replace(s, "***");
                                                    break;
                                                } else {
                                                    newValues[i] = values[i];
                                                }
                                            }
                                        }
                                    }
                                    return newValues;
                                }
                                if ("getParameterMap".equals(method.getName())) {
                                    Map<String, String[]> map = (Map<String, String[]>) method
                                            .invoke(request, args);
                                    Map<String, String[]> result = new HashMap<>();
                                    String[] newValues = new String[map.size()];
                                    map.entrySet().forEach(stringEntry -> {
                                        String[] values = stringEntry.getValue();
                                        for (int i = 0; i < values.length; i++) {
                                            if (StringUtils.isNotEmpty(values[i])) {
                                                for (String s : set) {
                                                    if (values[i].contains(s)) {
                                                        newValues[i] = values[i].replace(s, "***");
                                                        break;
                                                    } else {
                                                        newValues[i] = values[i];
                                                    }
                                                }
                                            }
                                            result.put(stringEntry.getKey(), newValues);
                                        }
                                    });
                                    return result;
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