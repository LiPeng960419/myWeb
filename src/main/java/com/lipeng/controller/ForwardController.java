package com.lipeng.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: lipeng6@ybm100.com
 * @Date: 2020/11/07 16:01
 */
@Controller
@Slf4j
public class ForwardController {

    @RequestMapping(value = "/toIndex", method = RequestMethod.GET)
    public String toIndex() {
        return "redirect:index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        log.info("redirect index page");
        return "index";
    }

    @RequestMapping(value = "/toForwardIndex", method = RequestMethod.GET)
    public String toforwardIndex() {
        return "forward:forwardIndex";
    }

    @RequestMapping(value = "/forwardIndex", method = RequestMethod.GET)
    public String forwardIndex() {
        log.info("forward index page");
        return "index";
    }

}