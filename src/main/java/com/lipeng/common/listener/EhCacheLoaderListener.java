package com.lipeng.common.listener;

import com.lipeng.service.UserService;
import com.lipeng.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * @Author: lipeng 910138
 * @Date: 2020/8/29 21:10
 */
@Slf4j
public class EhCacheLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("EhCacheLoaderListener start");
        EhCacheCacheManager cacheManager = SpringContextUtils.getBean(EhCacheCacheManager.class);
        UserService userService = SpringContextUtils.getBean(UserService.class);
        Cache myCache = cacheManager.getCache("myCache");
        myCache.put("user_list", userService.listUser());
        myCache.get("user_list", List.class).forEach(o -> log.info(o.toString()));
        log.info("EhCacheLoaderListener end");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("EhCacheLoaderListener destroy");
    }

}