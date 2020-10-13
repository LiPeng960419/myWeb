package com.lipeng.controller;

import com.alibaba.fastjson.JSON;
import com.lipeng.domain.QueryUser;
import com.lipeng.domain.User;
import com.lipeng.service.UserService;
import com.xiaoju.uemc.tinyid.client.utils.TinyId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lipeng 910138
 * @Date: 2020/6/5 16:01
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/list")
    public Map<String, Object> list() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<User> users = userService.listUser();
        map.put("success", true);
        map.put("list", users);
        return map;
    }

    @RequestMapping("/user/str")
    public String str() {
        List<User> users = userService.listUser();
        return JSON.toJSONString(users);
    }

    @RequestMapping("/user/page")
    public Map<String, Object> page(QueryUser queryUser) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<User> users = userService.userPage(queryUser);
        map.put("success", true);
        map.put("list", users);
        map.put("total", queryUser.getTotalCount());
        return map;
    }

    @RequestMapping("/user/pageAnno")
    public Map<String, Object> pageAnno(QueryUser queryUser) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<User> users = userService.userPageAnno(queryUser);
        map.put("success", true);
        map.put("list", users);
        map.put("total", queryUser.getTotalCount());
        return map;
    }

    @RequestMapping("/id")
    public Long id(String bizType) {
        return TinyId.nextId(bizType);
    }

    @RequestMapping("/ids")
    public List<Long> ids(String bizType, Integer batchSize) {
        return TinyId.nextId(bizType, batchSize);
    }

}