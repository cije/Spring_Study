package com.ce.controller;

import com.ce.domain.Account;
import com.ce.domain.Account1;
import com.ce.domain.User1;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 请求参数绑定
 */
@Controller
@RequestMapping("/param")
public class ParamController {
    /**
     * 请求参数绑定入门
     */
    @RequestMapping("/testParam")
    public String testParam(String username, String password) {
        System.out.println("请求参数绑定入门");
        System.out.println("你的用户名：" + username);
        System.out.println("你的密码：" + password);
        return "success";
    }

    /**
     * 将数据封装到JavaBean的类中
     */
    @RequestMapping("/saveAccount")
    public String saveAccount(Account account) {
        System.out.println("将数据封装到JavaBean的类中");
        System.out.println(account);
        return "success";
    }

    /**
     * 将数据封装到JavaBean的类中，类中有集合
     */
    @RequestMapping("/saveAccount1")
    public String saveAccount1(Account1 account) {
        System.out.println("将数据封装到JavaBean的类中，类中有集合");
        System.out.println(account);
        return "success";
    }

    /**
     * 自定义类型转换器
     */
    @RequestMapping("/saveUser")
    public String saveUser(User1 user1) {
        System.out.println("自定义类型转换器");
        System.out.println(user1);
        return "success";
    }

    /**
     * 获取原生API
     */
    @RequestMapping("/testServlet")
    public String testServlet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("获取原生API");
        System.out.println(request);
        HttpSession session = request.getSession();
        System.out.println(session);
        ServletContext servletContext = session.getServletContext();
        System.out.println(servletContext);
        System.out.println(response);
        return "success";
    }
}
