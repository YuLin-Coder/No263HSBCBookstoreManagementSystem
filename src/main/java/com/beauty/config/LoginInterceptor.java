package com.beauty.config;

import com.beauty.config.contants.Contants;
import com.beauty.config.exception.NeedLoginException;
import com.beauty.entity.UserEntity;
import com.beauty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if(!httpServletRequest.getRequestURI().endsWith("htm")){
            return true;
        }
        Cookie[] cookies =  httpServletRequest.getCookies();
        if(cookies==null || cookies.length==0){
            throw new NeedLoginException("需要登录");
        }
        String uid = "";
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("login_key_auth")){
                uid = cookie.getValue();
            }
        }
        if(StringUtils.isEmpty(uid)){
            throw new NeedLoginException("需要登录");
        }
        UserEntity userEntity = userService.selectById(uid);
        if(userEntity==null || userEntity.getStatus()==false){
            throw new NeedLoginException("需要登录");
        }
        Contants.USER_ENTITY_THREAD_LOCAL.set(userEntity);
        Cookie cookie = new Cookie("login_key_auth",userEntity.getId());
        cookie.setPath("/");
        cookie.setMaxAge(3600000);
        httpServletResponse.addCookie(cookie);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
