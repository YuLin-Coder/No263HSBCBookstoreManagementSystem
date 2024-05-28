package com.beauty.config;

import com.beauty.config.contants.Contants;
import com.beauty.config.exception.NeedLoginException;
import com.beauty.model.CustomerEntity;
import com.beauty.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
@Component
public class CustomerLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private CustomerService customerService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String uid = "";
        Cookie[] cookies =  httpServletRequest.getCookies();
        if(cookies==null || cookies.length==0){
        }else{
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("login_key_auth_customer")){
                    uid = cookie.getValue();
                }
            }
        }
        CustomerEntity userEntity = customerService.selectById(uid);
        if(userEntity==null){
            httpServletRequest.setAttribute("loginFlag",false);
        }else{
            httpServletRequest.setAttribute("loginFlag",true);
            httpServletRequest.setAttribute("loginCustomer",userEntity);
            Cookie cookie = new Cookie("login_key_auth_customer",userEntity.getId());
            cookie.setPath("/");
            cookie.setMaxAge(3600000);
            httpServletResponse.addCookie(cookie);
        }
        Contants.CUSTOMER_ENTITY_THREAD_LOCAL.set(userEntity);
        if(!httpServletRequest.getRequestURI().endsWith("do")){
            return true;
        }else{
            if(userEntity==null){
                throw new NeedLoginException("需要登录");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
