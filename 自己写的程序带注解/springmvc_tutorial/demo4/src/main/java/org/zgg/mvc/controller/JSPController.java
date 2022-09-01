package org.zgg.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 请求jsp页面
 */
@Controller
public class JSPController {

    @RequestMapping("/success")
    public String success(){
        return "success";
    }

}