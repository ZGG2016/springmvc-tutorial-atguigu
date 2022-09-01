package org.zgg.mvc.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 基于注解的异常处理
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {ArithmeticException.class, NullPointerException.class})
    public String test(Exception ex, Model model){
        model.addAttribute("ex",ex);
        return "error";
    }
}
