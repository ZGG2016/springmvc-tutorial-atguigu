package org.zgg.mvc.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SecondInterceptor implements HandlerInterceptor {
    /*
        多个拦截器的执行顺序:
            a> 若每个拦截器的preHandle()都返回true
               此时多个拦截器的执行顺序和拦截器在SpringMVC的配置文件的配置顺序有关：
                   preHandle()会按照配置的顺序执行，
                   postHandle()和afterComplation()会按照配置的反序执行
            b> 若某个拦截器的preHandle()返回了false
               preHandle()返回false和它之前的拦截器的preHandle()都会执行，
               postHandle()都不执行，
               返回false的拦截器之前的拦截器的afterComplation()会执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("--- SecondInterceptor preHandle ---");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("--- SecondInterceptor postHandle ---");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("--- SecondInterceptor afterCompletion ---");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
