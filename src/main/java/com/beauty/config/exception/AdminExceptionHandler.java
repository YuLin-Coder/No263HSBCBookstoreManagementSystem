package com.beauty.config.exception;

import com.beauty.config.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截
 */
@ControllerAdvice
@Slf4j
public class AdminExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(Exception e, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        Result result = null;
        if (e instanceof NeedLoginException) {
            result = Result.success("需要登录");
            result.setCode(10001);
        } else {
            e.printStackTrace();
            result = Result.error(e.getMessage());
            result.setData("");
        }
        mav.addObject("err_msg", result.getMsg());
        return mav;
    }

    /**
     * 判断是不是ajax请求
     *
     * @param request
     * @return
     */
    public boolean isAjax(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(requestType)) {
            return true;
        } else {
            return false;
        }
    }
}
