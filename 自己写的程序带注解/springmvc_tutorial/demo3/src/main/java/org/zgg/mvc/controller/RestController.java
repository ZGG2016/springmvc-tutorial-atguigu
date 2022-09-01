package org.zgg.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 使用RESTFul模拟用户资源的增删改查
 */
@Controller
public class RestController {
    /*
      使用RESTFul模拟用户资源的增删改查
      /user    GET     查询所有用户信息
      /user/1    GET     根据用户id查询用户信息
      /user    POST     添加用户信息
      /user/1    DELETE     删除用户信息
      /user    PUT     修改用户信息
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getAllUsers(){
        System.out.println("查询所有用户信息");
        return "success";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getUserById(){
        System.out.println("根据id查询用户信息");
        return "success";
    }

    @RequestMapping(value = "/user-post", method = RequestMethod.POST)
    public String insertUser(String username, String password){
        System.out.println("添加用户信息："+username+","+password);
        return "success";
    }

    /*
       要在 web.xml 中 添加HiddenHttpMethodFilter
       表单里添加 <input type="hidden" name="_method" value="PUT">
       表单的请求方法是POST
     */
    @RequestMapping(value = "/user-put", method = RequestMethod.PUT)
    public String updateUser(String username, String password){
        System.out.println("修改用户信息："+username+","+password);
        return "success";
    }

}
