package com.lipeng.controller;

import com.lipeng.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @Author: lipeng 910138
 * @Date: 2020/8/29 17:15
 */
@RestController
@Slf4j
@RequestMapping("/binding")
public class BindingController {

    // http://127.0.0.1:8080/myWeb/binding/getRequestParam?username=李鹏
    @RequestMapping(value = "/getRequestParam", method = RequestMethod.GET)
    public void getRequestParam(@RequestParam String username) {
        log.info("getRequestParam username:{}", username);
    }

    // http://127.0.0.1:8080/myWeb/binding/get?username=李鹏
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public void get(String username) {
        log.info("get username:{}", username);
    }

    // http://127.0.0.1:8080/myWeb/binding/getArray?username=aaa&username=bbb&username=ccc
    @RequestMapping(value = "/getArray", method = RequestMethod.GET)
    public void getArray(String[] username) {
        log.info("getArray username:{}", Arrays.toString(username));
    }

    // http://127.0.0.1:8080/myWeb/binding/postArray
    // {"username","aaa,bbb,ccc"}
    @RequestMapping(value = "/postArray", method = RequestMethod.POST)
    public void postArray(String[] username) {
        log.info("postArray username:{}", Arrays.toString(username));
    }

    // http://127.0.0.1:8080/myWeb/binding/getObject?username=李鹏
    @RequestMapping(value = "/getObject", method = RequestMethod.GET)
    public void getObject(User user) {
        log.info("getObject user:{}", user.toString());
    }

    // http://127.0.0.1:8080/myWeb/binding/postObject   username 李鹏
    @RequestMapping(value = "/postObject", method = RequestMethod.POST)
    public void postObject(User user) {
        log.info("postObject user:{}", user.toString());
    }

    // http://127.0.0.1:8080/myWeb/binding/postJson {"username":"李鹏"}
    @RequestMapping(value = "/postJson", method = RequestMethod.POST)
    public void postJson(@RequestBody User user) {
        log.info("postObject user:{}", user.toString());
    }

}