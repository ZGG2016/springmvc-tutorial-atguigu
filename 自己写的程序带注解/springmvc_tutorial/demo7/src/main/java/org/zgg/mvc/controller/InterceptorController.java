package org.zgg.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
// 拦截器
@Controller
public class InterceptorController {

    @RequestMapping("/**/testInterceptor")
    public String testInterceptor(){
        return "success";
    }

    @RequestMapping("/testExceptionHandler")
    public String testException(){
        System.out.println(1/0);
        return "success";
    }
}
