package com.ce.controller;

import com.ce.exception.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exception")
public class ExceptionController {

    @RequestMapping("testException")
    public String testException() throws Exception {
        System.out.println("testException执行了");

        try {
            // 模拟异常
            int a = 1 / 0;
        } catch (Exception e) {
            // 打印异常信息
            e.printStackTrace();
            // 抛出自定义异常信息
            throw new SysException("不妙，出现错误啦....");
        }

        return "success";
    }
}
