package com.ce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class HelloController {
    /**
     * 入门案例
     */
    @RequestMapping("/hello")
    public String sayHello() {
        System.out.println("Hello");
        return "success";
    }

    /**
     * RequestMapping注解
     */
    @RequestMapping(value = "/testMapping",
            method = {RequestMethod.GET, RequestMethod.POST},
            params = {"username"},
            headers = {"Accept"})
    public String testRequestMapping() {
        System.out.println("测试RequestMapping注解");
        return "success";
    }
}
