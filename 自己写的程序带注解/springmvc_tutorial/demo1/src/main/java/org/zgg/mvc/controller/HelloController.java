package org.zgg.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    //  通过@RequestMapping注解，可以通过请求路径 匹配要处理的具体的请求
    //  / 表示的当前工程的上下文路径
    @RequestMapping("/")
    public String index(){
        // 通过视图解析器解析，定位到index.html
        return "index";
    }

    @RequestMapping("/target")
    public String toTarget(){
        return "target";
    }
}
