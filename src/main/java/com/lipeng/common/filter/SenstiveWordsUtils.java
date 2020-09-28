package com.lipeng.common.filter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

/**
 * @Author: lipeng 910138
 * @Date: 2020/9/28 10:15
 */
@Slf4j
public class SenstiveWordsUtils {

    public static void init(FilterConfig filterConfig, Set<String> set) {
        ServletContext servletContext = filterConfig.getServletContext();
        String filePath = servletContext.getRealPath("/WEB-INF/classes/sensitivewords.txt");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                set.add(line);
            }
        } catch (Exception e) {
            log.error("SenstiveWordsUtils init error", e);
        } finally {
            IOUtils.closeQuietly(bufferedReader);
        }
    }

}
