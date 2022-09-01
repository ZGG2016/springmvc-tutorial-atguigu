package org.zgg.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * `@RequestMapping` 注解的属性
 */

/*
 * RequestMapping标识的位置：
 *   - 标识一个类：设置映射请求的请求路径的初始信息
 *   - 标识一个方法：设置映射请求请求路径的具体信息
 * 也就是说，先匹配到 `/reqmapping` 再匹配到 `/test-1` --> `/reqmapping/test-1`
 *
 * 这就可以在不同的控制器下，定义相同的匹配路径，（相当于名称空间A中定义了方法C,名称空间B中也定义了方法C）
 * 比如，存在另一个类，其中也定义了相同的success方法，如果没有用RequestMapping标识类，那么就会报错，
 * 如果使用RequestMapping标识类，那么就会根据路径，匹配到对应的方法上
 *
 **/
//@RequestMapping("/reqmapping")
@Controller
public class ReqMappingController {

    @RequestMapping("/test-0")
    public String success(){
        return "success";
    }

    /*
    * @RequestMapping注解的value属性:
    *   - 通过请求的请求地址匹配请求映射
    *   - value属性是一个字符串类型的数组，表示该请求映射能够匹配多个请求地址所对应的请求（满足一个即可）
    *   - value属性必须设置，至少通过请求地址匹配请求映射
    * */
    @RequestMapping(value = {"/test-1", "/test-2"})
    public String success2(){
        return "success";
    }

    /*
    * @RequestMapping注解的method属性:
    *     - 通过请求的请求方式（get或post）匹配请求映射
    *     - 如果不设置，那么只要匹配上了value属性，不管POST还是GET，都能匹配成功
    *     - method属性值是一个RequestMethod类型的数组，表示该请求映射能够匹配多种请求方式的请求（满足一个即可）
    *     - 若当前请求的请求地址满足请求映射的value属性，但是请求方式不满足method属性，
    *        则浏览器报错 405：Request method 'POST' not supported
    * */
    @RequestMapping(
            value = "/test-3",
//            method = RequestMethod.GET
            method = { RequestMethod.GET, RequestMethod.POST}
    )
    public String success3(){
        return "success";
    }

    /*
    * SpringMVC中提供了@RequestMapping的派生注解
    *   - 处理get请求的映射-->@GetMapping
    *   - 处理post请求的映射-->@PostMapping
    *   - 处理put请求的映射-->@PutMapping
    *   - 处理delete请求的映射-->@DeleteMapping
    *
    * 目前浏览器只支持get和post，若在form表单提交时，为method设置了其他请求方式的字符串（put或delete），则按照默认的请求方式get处理
    *
    * 若当前请求的请求地址满足请求映射的value属性，但是请求方式不满足method属性，则浏览器报错 405：Request method 'POST' not supported
    * */
    @PostMapping("/test-4")
    public String success4(){
        return "success";
    }


    /*
    @RequestMapping注解的params属性:
      - 通过请求的请求参数匹配请求映射
      - 是一个字符串类型的数组，可以通过四种表达式设置请求参数和请求映射的匹配关系
          - "param"：要求请求映射所匹配的请求必须携带param请求参数
          - "!param"：要求请求映射所匹配的请求必须不能携带param请求参数
          - "param=value"：要求请求映射所匹配的请求必须携带param请求参数且param=value
          - "param!=value"：要求请求映射所匹配的请求必须携带param请求参数但是param!=value

       若当前请求满足@RequestMapping注解的value和method属性，
       但是不满足params属性，此时页面回报错400：
       Parameter conditions "username, password!=123456" not met for actual request parameters: userna....
     */
    @RequestMapping(
            value = "/test-5",
//            params = {"username","password!=123456"},
            params = {"username=admin", "password=p1234"}  // （全部满足才行）
    )
    public String success5(){
        return "success";
    }

    /*
      @RequestMapping注解的headers属性:
         - 通过请求的请求头信息匹配请求映射
         - 是一个字符串类型的数组，可以通过四种表达式设置请求头信息和请求映射的匹配关系
            - "header"：要求请求映射所匹配的请求必须携带header请求头信息
            - "!header"：要求请求映射所匹配的请求必须不能携带header请求头信息
            - "header=value"：要求请求映射所匹配的请求必须携带header请求头信息且header=value
            - "header!=value"：要求请求映射所匹配的请求必须携带header请求头信息且header!=value

          若当前请求满足@RequestMapping注解的value和method属性，
          但是不满足headers属性，此时页面显示404错误，即资源未找到
     */
    @RequestMapping(
            value = "/test-6",
            headers = {"Host=localhost:8080"}
    )
    public String success6(){
        return "success";
    }

    // ？ 表示任意的单个字符   但不能是 / ?
    // * 表示任意的0个或多个字符
    // ** 表示任意的一层或多层目录
    // 注意：在使用 ** 时，只能使用 /**/xxx 的方式
    @RequestMapping(
//            value = "/test-7/a?b/testant"
//            value = "/test-7/a*b/testant"
            value = "/test-7/**/testant"
    )
    public String success7(){
        return "success";
    }

    /*
        SpringMVC路径中的占位符常用于RESTful风格中
        在请求路径中，将某些数据通过路径的方式传输到服务器中，就可以在相应的 @RequestMapping 注解的value属性中通过占位符 {xxx} 表示传输的数据，
        再通过 @PathVariable 注解，将占位符所表示的数据赋值给控制器方法的形参
     */
    @RequestMapping(
            value = "/test-8/{username}/{password}"
    )
    public String success8(@PathVariable("username") String username, @PathVariable("password") String password){
        System.out.println("username: " + username + " | password: " + password);
        return "success";
    }
}
