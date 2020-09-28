package com.lipeng.common.filter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
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
public class SensitiveWordsFromValidateFilter implements Filter {

    private final Set<String> set = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) {
        SenstiveWordsUtils.init(filterConfig, set);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        HttpServletRequest req = (HttpServletRequest) request;
        SensitiveWordsWrapper wrapper = new SensitiveWordsWrapper(req);
        Map<String, String[]> map = wrapper.getParams();
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
        wrapper.setParameterMap(map);
    }

    @Override
    public void destroy() {

    }

}
