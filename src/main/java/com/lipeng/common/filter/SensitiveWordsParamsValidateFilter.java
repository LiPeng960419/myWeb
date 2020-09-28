package com.lipeng.common.filter;

import java.io.IOException;
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
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

/**
 * @Author: lipeng 910138
 * @Date: 2020/9/28 10:09
 */
@Slf4j
public class SensitiveWordsParamsValidateFilter implements Filter {

    private final Set<String> set = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) {
        SenstiveWordsUtils.init(filterConfig, set);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Map<String, String[]> map = request.getParameterMap();
        SensitiveWordsParamsWrapper wrapper = new SensitiveWordsParamsWrapper(req);
        Map<String, String[]> result = new HashMap<>();
        map.entrySet().forEach(stringEntry -> {
            String[] newValues = new String[1];
            String[] values = stringEntry.getValue();
            for (int i = 0; i < values.length; i++) {
                if (StringUtils.isNotEmpty(values[i])) {
                    for (String s : set) {
                        if (values[i].contains(s)) {
                            newValues[0] = values[i].replace(s, "***");
                            break;
                        } else {
                            newValues[0] = values[i];
                        }
                    }
                }
                result.put(stringEntry.getKey(), newValues);
            }
        });
        wrapper.setParameterMap(result);
        chain.doFilter(wrapper, response);
    }

    @Override
    public void destroy() {

    }

}
