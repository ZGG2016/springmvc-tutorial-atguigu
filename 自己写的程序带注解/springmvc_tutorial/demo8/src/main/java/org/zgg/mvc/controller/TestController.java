package org.zgg.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 使用配置类和注解代替web.xml和SpringMVC配置文件的功能
 */
@Controller
public class TestController {

    @RequestMapping("/")
    public String test(){
        return "index";
    }

}
