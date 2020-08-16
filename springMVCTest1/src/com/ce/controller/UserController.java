package com.ce.controller;

import com.ce.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 返回String
     */
    @RequestMapping("/testString")
    public String testString(Model model) {
        System.out.println("testSTring方法执行了");
        //模拟从数据库查询出User对象
        User user = new User();
        user.setUsername("美美");
        user.setPassword("123");
        user.setAge(18);
        model.addAttribute("user", user);
        return "success";
    }

    /**
     * 是void
     * 请求转发一次请求，不用编写项目名称
     */
    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("testVoid方法执行了");
        // 编写请求转发的程序
        // request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request, response);

        // 重定向
        // response.sendRedirect(request.getContextPath()+"/index.jsp");


        // 直接进行响应
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("你好");
        return;
    }

    /**
     * 返回ModelAndView
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("testModelAndView方法执行了");
        //模拟从数据库查询出User对象
        User user = new User();
        user.setUsername("美美");
        user.setPassword("123");
        user.setAge(18);
        // 把user对象存储到mv对象中，也会把user对象存入到request对象
        modelAndView.addObject("user", user);

        //跳转到哪个页面
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /**
     * 使用关键字的方式进行转发或重定向
     */
    @RequestMapping("/testForwardOrRedirect")
    public String testForwardOrRedirect() {
        System.out.println("testForwardOrRedirect方法执行了");
        // 请求转发
        // return "forward:/WEB-INF/pages/success.jsp";

        //重定向
        return "redirect:/index.jsp";
    }

    /**
     * 模拟异步请求响应
     */
    @RequestMapping("/testAjax")
    public @ResponseBody
    User testAjax(@RequestBody User user) {
        System.out.println("testAjax方法执行了");
        // 客户端发送ajax的请求，传的是json字符串，后端把json字符串封装到user中
        System.out.println(user);
        // 作响应，模拟查询数据库
        user.setAge(80);

        // 作响应
        return user;
    }
}
