package com.lipeng.common.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: lipeng 910138
 * @Date: 2020/9/28 11:05
 */
@Slf4j
public class SensitiveWordsJsonValidateFilter implements Filter {

    private final Set<String> set = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) {
        SenstiveWordsUtils.init(filterConfig, set);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        SensitiveWordsJsonWrapper wrapper = new SensitiveWordsJsonWrapper(
                (HttpServletRequest) request);
        String line = null;
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = wrapper.getReader()) {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            log.error("getReader error", e);
        }
        JSONObject jsonObject = JSON.parseObject(sb.toString());
        Map<String, Object> map = new HashMap<String, Object>();
        map = JSON.toJavaObject(jsonObject, Map.class);
        for (Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String) {
                String v = (String) value;
                for (String s : set) {
                    if (v.contains(s)) {
                        map.put(entry.getKey(), v.replace(s, "***"));
                        break;
                    }
                }
            }
        }
        String json = JSON.toJSONString(map);
        wrapper.setBody(json.getBytes(StandardCharsets.UTF_8));
        chain.doFilter(wrapper, response);
    }

    @Override
    public void destroy() {

    }
}
