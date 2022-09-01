package org.zgg.converter.controller;

import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zgg.converter.bean.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
// HttpMessageConverter报文信息转换器
/*
    @RestController注解是springMVC提供的一个复合注解，
    标识在控制器的类上，
    就相当于为类添加了@Controller注解，
    并且为其中的每个方法添加了@ResponseBody注解
 */
//@RestController
@Controller
public class HttpMessageConverterController {

    /*
       @RequestBody 可以获取请求体，
          需要在控制器方法设置一个形参，使用 @RequestBody 进行标识，
          当前请求的请求体就会为当前注解所标识的形参赋值
     */
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String requestBody) {
        System.out.println("requestBody: " + requestBody);
        return "success";
    }

    /*
       RequestEntity封装请求报文的一种类型，
          需要在控制器方法的形参中设置该类型的形参，
          当前请求的请求报文就会赋值给该形参
     */
    @RequestMapping("/testRequestEntity")
    public String testRequestEntity(RequestEntity<String> requestEntity) {
        System.out.println("请求头: " + requestEntity.getHeaders());
        System.out.println("请求方法: " + requestEntity.getMethod());
        System.out.println("请求体: " + requestEntity.getBody());
        return "success";
    }

    @RequestMapping("/testResponseServletApi")
    public void testResponse(HttpServletResponse response) throws IOException {
        response.getWriter().print("hello response");
    }

    /*
       @ResponseBody用于标识一个控制器方法，
         可以将该方法的返回值直接作为响应报文的响应体响应到浏览器
     */
    @ResponseBody
    @RequestMapping("/testResponseBody")
    public String testResponseBody() throws IOException {
        return "success";
    }

    /*
       @ResponseBody处理json的步骤：
          1. 导入jackson的依赖
          2. 在SpringMVC的核心配置文件中开启mvc的注解驱动，
             此时在HandlerAdaptor中会自动装配一个消息转换器：MappingJackson2HttpMessageConverter，
             可以将响应到浏览器的Java对象转换为Json格式的字符串
          3. 在处理器方法上使用@ResponseBody注解进行标识
          4. 将Java对象直接作为控制器方法的返回值返回，就会自动转换为Json格式的字符串
     */
    @ResponseBody
    @RequestMapping("/testResponseBodyUser")
    public User testResponseBodyUser() throws IOException {
        return new User(1001, "admin", "123456", 23, "男");
    }

    // SpringMVC处理ajax
    @ResponseBody
    @RequestMapping("/testAxios")
    public String testAxios(String username, String password) {
        System.out.println(username + "," + password);
        return "hello,axios";
    }
}
