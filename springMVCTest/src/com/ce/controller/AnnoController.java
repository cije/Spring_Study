package com.ce.controller;

import com.ce.domain.User1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.time.LocalDate;
import java.util.Map;

/**
 * 常用注解
 */
@Controller
@RequestMapping("/anno")
@SessionAttributes(value = {"msg"}) //把msg=啦啦啦啦啦存入到session域中
public class AnnoController {
    /**
     * testRequestParam 注解
     */
    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam(name = "username") String name) {
        System.out.println("执行了");
        System.out.println(name);
        return "success";
    }

    /**
     * 获取请求体的内容
     *
     * @param body
     * @return
     */
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String body) {
        System.out.println("执行了");
        System.out.println(body);
        return "success";
    }

    /**
     * PathVariable 注解
     */
    @RequestMapping("/testPathVariable/{sid}")
    public String testPathVariable(@PathVariable(name = "sid") String id) {
        System.out.println("执行了");
        System.out.println(id);
        return "success";
    }

    /**
     * RequestHeader 注解 获取请求头信息
     */
    @RequestMapping("/testRequestHeader")
    public String testRequestHeader(@RequestHeader(name = "Accept") String header) {
        System.out.println("获取请求头信息");
        System.out.println(header);
        return "success";
    }

    /**
     * CookieValue 注解 获取Cookie的值
     */
    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID") String cookieValue) {
        System.out.println("获取Cookie的值");
        System.out.println(cookieValue);
        return "success";
    }

    /**
     * ModelAttribute 注解
     */
    @RequestMapping("/testModelAttribute")
    public String testModelAttribute(@ModelAttribute(value = "test") User1 user1) {
        System.out.println("ModelAttribute 注解");
        System.out.println(user1);
        return "success";
    }

    /*

    @ModelAttribute
    public User1 showUser(String uname){
        System.out.println("showUser执行了");
        //通过用户名查询数据库（模拟）
        User1 user1=new User1();
        user1.setUname(uname);
        user1.setAge(20);
        user1.setDate(LocalDate.now());
        return user1;
    }

    */

    /**
     * 该方法会先执行
     */
    @ModelAttribute
    public void showUser(String uname, Map<String,User1> map) {
        System.out.println("showUser执行了");
        //通过用户名查询数据库（模拟）
        User1 user1 = new User1();
        user1.setUname(uname);
        user1.setAge(20);
        user1.setDate(LocalDate.now());
        map.put("test",user1);
    }

    /**
     * SessionAttributes 注解
     */
    @RequestMapping("/testSessionAttributes")
    public String testSessionAttributes(Model model) {
        System.out.println("testSessionAttributes");
        //底层会存储到request域对象中
        model.addAttribute("msg","啦啦啦啦啦");
        return "success";
    }

    /**
     * 获取session值
     * @param model
     * @return
     */
    @RequestMapping("/getSessionAttributes")
    public String getSessionAttributes(Model model) {
        System.out.println("getSessionAttributes");
        Object msg = model.getAttribute("msg");
        System.out.println(msg);
        return "success";
    }
    /**
     * 清除session值
     */
    @RequestMapping("/removeSessionAttributes")
    public String removeSessionAttributes(SessionStatus status) {
        System.out.println("removeSessionAttributes");
        status.setComplete();
        return "success";
    }
}
