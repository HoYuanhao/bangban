package com.xunyanhui.security.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-26
 * <p>Version: 1.0
 */
@Controller
public class ServiceController {

    @RequestMapping("/hello")
    public String hello1(String[] param1, String param2) {
        return "hello" + param1[0] + param1[1] + param2;
    }
    @RequestMapping("/hello1")
    public String hello(String[] param1, String param2) {
        return "hello" + param1[0] + param1[1] + param2;
    }
}
