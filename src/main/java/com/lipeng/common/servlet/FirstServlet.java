package com.lipeng.common.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: lipeng 910138
 * @Date: 2020/4/26 16:35
 */
public class FirstServlet extends HttpServlet {

    private static final long serialVersionUID = 9219374599787287476L;
    private static Logger log = LoggerFactory.getLogger(FirstServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String domain = req.getServerName();
        log.info("domain:{}", domain);
        ServletContext servletContext = this.getServletContext();
        // 输出/myWeb
        log.info("getContextPath:{}", servletContext.getContextPath());
        // D:\apache-tomcat-8.5.41\webapps\myWeb\
        log.info("getRealPath:{}", servletContext.getRealPath(""));
        // D:\apache-tomcat-8.5.41\webapps\myWeb\WEB-INF\classes\jdbc.properties
        log.info("getRealPath:{}", servletContext.getRealPath("/WEB-INF/classes/jdbc.properties"));
        // 获取1.txt在tomcat下的位置
        log.info("getRealPath:{}", servletContext.getRealPath("/upload/1.txt"));

        String resourcePath = FirstServlet.class.getClassLoader().getResource("").getPath();
        log.info("resourcePath:{}", resourcePath);

        String firstServletResourcePath = FirstServlet.class.getClassLoader()
                .getResource("com/lipeng/common/servlet/FirstServlet.class").getPath();
        log.info("firstServletResourcePath:{}", firstServletResourcePath);

        test01();
        test02();
        test03();

        createFile(new File(getServletContext().getRealPath("/WEB-INF/classes/jdbc.properties")));
        deleteFile(getServletContext().getRealPath("/upload") + File.separatorChar + "jdbc.properties");
    }

    /*
    获取class下的文件
     */
    private void test01() throws IOException {
        InputStream inputStream = getServletContext().getResourceAsStream("/WEB-INF/classes/jdbc.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        log.info("test01 username:{}", properties.getProperty("jdbc.username"));
    }

    private void test02() throws IOException {
        String path = getServletContext().getRealPath("/WEB-INF/classes/jdbc.properties");
        Properties properties = new Properties();
        properties.load(new FileInputStream(path));
        log.info("test02 username:{}", properties.getProperty("jdbc.username"));
    }

    /*
    获取webapp下的文件
     */
    private void test03() throws IOException {
        String path = getServletContext().getRealPath("/upload/1.txt");
        Properties properties = new Properties();
        properties.load(new FileInputStream(path));
        log.info("test03 username:{}", properties.getProperty("jdbc.username"));
    }

    private void createFile(File file) {
        String path = getServletContext().getRealPath("/upload");
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path + File.separatorChar + file.getName());
            fileInputStream = new FileInputStream(file);
            IOUtils.copy(new FileInputStream(file), fileOutputStream);
            log.info("file loaction:{}", getServletContext().getContextPath() + "/upload" + File.separatorChar + file.getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fileInputStream);
            IOUtils.closeQuietly(fileOutputStream);
        }
    }

    private void deleteFile(String path) {
        File file = new File(path);
        if (file != null && file.exists()) {
            boolean isSuccess = file.delete();
            log.info("删除文件{}{}", file.getName(), isSuccess ? "成功" : "失败");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        doGet(req, resp);
    }

}