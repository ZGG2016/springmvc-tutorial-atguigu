package org.zgg.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  ThymeleafView  转发视图  重定向视图
 */
@Controller
public class ViewController {

    /*
        ThymeleafView:
           当控制器方法中所设置的视图名称没有任何前缀时，
           此时的视图名称会被SpringMVC配置文件中所配置的视图解析器解析，
           视图名称拼接 视图前缀 和 视图后缀 所得到的最终路径，会通过 转发 的方式实现跳转
     */
    @RequestMapping("/test-ThymeleafView")
    public String testThymeleafView(){
        return "success";
    }

    /*
       转发视图:
          SpringMVC中默认的转发视图是 InternalResourceView
          SpringMVC中创建转发视图的情况：
                当控制器方法中所设置的视图名称以"forward:"为前缀时，创建InternalResourceView视图，
                此时的视图名称不会被SpringMVC配置文件中所配置的视图解析器解析，
                而是会将前缀"forward:"去掉，剩余部分作为最终路径通过转发的方式实现跳转
                例如"forward:/"，"forward:/employee"
     */
    @RequestMapping("/test-forward")
    public String testForward(){
        return "forward:/test-ThymeleafView";
    }

    /*
       重定向视图:
           SpringMVC中默认的重定向视图是 RedirectView

           当控制器方法中所设置的视图名称以"redirect:"为前缀时，创建RedirectView视图，
           此时的视图名称不会被SpringMVC配置文件中所配置的视图解析器解析，
           而是会将前缀"redirect:"去掉，剩余部分作为最终路径通过重定向的方式实现跳转
           例如"redirect:/"，"redirect:/employee"


     */
    @RequestMapping("/test-redirect")
    public String testRedirect(){
        return "redirect:/test-ThymeleafView";
    }

    /*
      【转发和重定向区别】
        1、请求次数
           重定向是浏览器向服务器发送一个请求并收到响应后再次向一个新地址发出请求，
           转发是服务器收到请求后为了完成响应跳转到一个新的地址；
           重定向至少请求两次，转发请求一次；

        2、地址栏不同
           重定向地址栏会发生变化，
           转发地址栏不会发生变化；

        3、是否共享数据
           重定向两次请求不共享数据，
           转发一次请求共享数据（在request级别使用信息共享，使用重定向必然出错）；

        4、跳转限制
           重定向可以跳转到任意URL，
           转发只能跳转本站点资源；

        5、发生行为不同
           重定向是客户端行为，
           转发是服务器端行为；
     */
}
