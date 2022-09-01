package org.zgg.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zgg.mvc.bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * SpringMVC获取请求参数
 */
@Controller
public class ParamController {

    /*
       通过ServletAPI获取请求参数

       将HttpServletRequest作为控制器方法的形参，
          此时HttpServletRequest类型的参数表示封装了当前请求的请求报文的对象
     */
    @RequestMapping("/test-servletapi")
    public String testServletAPI(HttpServletRequest request){
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + " - " + password);
        return "success";
    }

    /*
      通过控制器方法的形参获取请求参数

      在控制器方法的形参位置，设置和请求参数【同名】的形参，
          当浏览器发送请求，匹配到请求映射时，在DispatcherServlet中就会将请求参数赋值给相应的形参

      如果是多个同名请求参数，可以以字符串形式接收，也可以以字符串数组形式接收
          若使用字符串类型的形参，最终结果为请求参数的每一个值之间使用逗号进行拼接
    */
//    @RequestMapping("/testparam-1")
//    public String testParam1(String username, String password, String hobby){
//        System.out.println(username + " - " + password + " - " + hobby);
//        return "success";
//    }
    @RequestMapping("/testparam-1")
    public String testParam1(String username, String password, String[] hobby){

        System.out.println(username + " - " + password + " - " + Arrays.toString(hobby));
        return "success";
    }

    /*
        上面的例子中，形参和请求参数具有相同的名字。但是，当请求参数的名字和形参不同时，那就匹配不到，输出null了，
        所以 @RequestParam 注解，就可以将不同名的二者联系到一起

        @RequestParam
         将请求参数和控制器方法的形参创建映射关系
         一共有三个属性：
            - value：指定为形参赋值的请求参数的参数名
            - required：设置是否必须传输此请求参数，默认值为true
                 若设置为true时，则当前请求必须传输value所指定的请求参数，
                      若没有传输该请求参数，且没有设置defaultValue属性，则页面报错400：Required String parameter 'xxx' is not present；
                 若设置为false，则当前请求不是必须传输value所指定的请求参数，若没有传输，则注解所标识的形参的值为null
            - defaultValue：不管required属性值为true或false，当value所指定的请求参数没有传输或传输的值为""时，则使用默认值为形参赋值


         @RequestHeader 将请求头信息和控制器方法的形参创建映射关系
         @CookieValue 将cookie数据和控制器方法的形参创建映射关系

         @RequestHeader 和 @CookieValue注解一共有三个属性：value、required、defaultValue，用法同@RequestParam
     */
    @RequestMapping("/testparam-2")
    public String testParam2(
            @RequestParam(value = "user_name", required = true, defaultValue = "zhangsan") String username,
            String password,
            String[] hobby,
            @RequestHeader(value = "hehe", required = true, defaultValue = "haha") String host,
            @CookieValue("JSESSIONID") String JSESSIONID){

        System.out.println(username + " - " + password + " - " + Arrays.toString(hobby));
        System.out.println("host: " +host);
        System.out.println("JSESSIONID: " +JSESSIONID);
        return "success";
    }

    /*
       通过POJO获取请求参数

       可以在控制器方法的形参位置设置一个实体类类型的形参，
            此时若浏览器传输的【请求参数的参数名和实体类中的属性名一致】，那么请求参数就会为此属性赋值
     */
    @RequestMapping("/testparam-3")
    public String testParam3(User user){

        System.out.println(user);
        return "success";
    }
}
