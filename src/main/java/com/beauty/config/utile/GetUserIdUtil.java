package com.beauty.config.utile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassDesc 获取登录用户的ID
 */
public class GetUserIdUtil {

    /**
     * 获取当前登录用户的ID
     * @param request
     * @return
     */
    public static String getLoginUserId(HttpServletRequest request){
        String value = "";
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {//如果请求中存在Cookie
            for(Cookie c : cookies) {//遍历所有Cookie
                if(c.getName().equals("login_key_auth_customer")){
                    value = c.getValue();
                    break;
                }
            }
        }
        return value;
    }
}
