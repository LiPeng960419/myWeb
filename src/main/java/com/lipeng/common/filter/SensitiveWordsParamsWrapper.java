package com.lipeng.common.filter;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @Author: lipeng 910138
 * @Date: 2020/9/28 10:07
 */
public class SensitiveWordsParamsWrapper extends HttpServletRequestWrapper {

    // 用于存储请求参数
    private Map<String, String[]> params = new HashMap<String, String[]>();

    public SensitiveWordsParamsWrapper(HttpServletRequest request) {
        super(request);
    }

    /**
     * 添加参数到map中
     *
     * @param extraParams
     */
    public void setParameterMap(Map<String, String[]> newParams) {
        this.params = newParams;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return this.params;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return new Vector<String>(this.params.keySet()).elements();
    }

    /**
     * 重写getParameter，代表参数从当前类中的map获取
     *
     * @param name
     * @return
     */
    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    /**
     * 重写getParameterValues方法，从当前类的 map中取值
     *
     * @param name
     * @return
     */
    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }

}