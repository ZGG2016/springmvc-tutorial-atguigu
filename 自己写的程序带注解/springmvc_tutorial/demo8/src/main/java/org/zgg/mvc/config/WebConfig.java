package org.zgg.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.spring5.view.reactive.ThymeleafReactiveViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.zgg.mvc.interceptor.FirstInterceptor;

import java.util.List;
import java.util.Properties;

/**
 * 代替SpringMVC的配置文件：
 * 1、扫描组件   2、视图解析器     3、view-controller    4、default-servlet-handler
 * 5、mvc注解驱动    6、文件上传解析器   7、异常处理      8、拦截器
 */

//将当前类标识为一个配置类
@Configuration
//1、扫描组件
@ComponentScan("org.zgg.mvc")
//5、mvc注解驱动
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

//    4、default-servlet-handler
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

//    8、拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        FirstInterceptor interceptor = new FirstInterceptor();
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }

//    3、view-controller
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/hello").setViewName("hello");
    }

    // 6、文件上传解析器
    @Bean
    public MultipartResolver multipartResolver(){
        return new CommonsMultipartResolver();
    }

    // 7、异常处理
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        Properties prop = new Properties();
        prop.setProperty("java.lang.ArithmeticException","error");
        exceptionResolver.setExceptionMappings(prop);
        exceptionResolver.setExceptionAttribute("ex");
    }

    // 2、视图解析器
    // 生成视图解析器并未解析器注入模板引擎
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine springTemplateEngine){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(springTemplateEngine);
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }
    // 生成模板引擎并为模板引擎注入模板解析器
    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver iTemplateResolver){
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(iTemplateResolver);
        return engine;
    }
    // 配置生成模板解析器
    @Bean
    public ITemplateResolver templateResolver(){
        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(context.getServletContext());
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }
}
