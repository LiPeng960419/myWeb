package com.lipeng.common.servlet;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Properties;

/**
 * @Author: lipeng 910138
 * @Date: 2020/4/26 16:35
 */
public class FirstServlet extends HttpServlet {

    private static final long serialVersionUID = 9219374599787287476L;
    private static final Logger log = LoggerFactory.getLogger(FirstServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getParameter("username");
        // 比如http://127.0.0.1:8080/myWeb/firstServlet/a/b
        // 匹配web.xml里面的servlet /firstServlet
        log.info("getServletPath:{}", req.getServletPath());
        // 如果匹配到web.xml里面servlet path就是访问的url减去servlepath = /a/b
        log.info("getPathInfo:{}", req.getPathInfo());
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