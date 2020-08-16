package com.ce.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理器
 */
public class SysExceptionResolver implements HandlerExceptionResolver {
    /**
     * 处理异常业务逻辑
     *
     * @param httpServletRequest  request
     * @param httpServletResponse response
     * @param o
     * @param e
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        // 获取到异常对象
        SysException exception = null;
        if (e instanceof SysException) {
            exception = (SysException) e;
        } else {
            exception = new SysException("系统正在维护...");
        }
        // 创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMsg", e.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
