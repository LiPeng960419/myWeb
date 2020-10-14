package com.lipeng.controller;

import com.lipeng.common.udp.manager.UdpManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lipeng 910138
 * @Date: 2020/10/14 15:47
 */
@RestController
@RequestMapping("/udp")
public class UdpTestController {

    @RequestMapping("/sendData")
    public String sendData(String data) {
        UdpManager.sendData(data);
        return "success";
    }

}