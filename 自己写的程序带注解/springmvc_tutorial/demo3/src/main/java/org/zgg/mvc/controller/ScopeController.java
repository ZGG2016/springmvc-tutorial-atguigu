package org.zgg.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 域对象共享数据
 */
@Controller
public class ScopeController {

    // 使用ServletAPI向request域对象共享数据
    @RequestMapping("/test-servletapi")
    public String testServletAPI(HttpServletRequest request){
        request.setAttribute("name","zhangsan");
        return "success";
    }


    // 使用 modelandview 向request域对象共享数据
    @RequestMapping("/test-modelandview")
    public ModelAndView testModelAndView(){
        ModelAndView modelAndView = new ModelAndView();
        // 设置共享数据
        modelAndView.addObject("name","zhangsan");
        // 设置跳转的页面
        modelAndView.setViewName("success");
        return modelAndView;
    }

    // 使用 model 向request域对象共享数据
    @RequestMapping("/test-model")
    public String testModel(Model model){
        // 设置共享数据
        model.addAttribute("name","zhangsan");
        return "success";
    }

    // 使用 map 向request域对象共享数据
    @RequestMapping("/test-map")
    public String testMap(Map<String,Object> map){
        // 设置共享数据
        map.put("name","zhangsan");
        return "success";
    }

    // 使用 modelmap 向request域对象共享数据
    @RequestMapping("/test-modelMap")
    public String testMap(ModelMap modelMap){
        // 设置共享数据
        modelMap.addAttribute("name","zhangsan");
        return "success";
    }

    // 向session域共享数据
    @RequestMapping("/test-session")
    public String testSession(HttpSession httpSession){
        // 设置共享数据
        httpSession.setAttribute("name","zhangsan");
        return "success";
    }

    // 向application域共享数据
    @RequestMapping("/test-application")
    public String testApplication(HttpSession httpSession){
        // 设置共享数据
        httpSession.setAttribute("name","zhangsan");
        return "success";
    }
}
