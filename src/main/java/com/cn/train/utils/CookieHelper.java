package com.cn.train.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieHelper {

    public static String getCookieValue(HttpServletRequest request, String key){
        Cookie[]cookies = request.getCookies();
        Cookie c = null;
        if(cookies!=null){
            for(int i=0;i<cookies.length;i++){
                 c =  cookies[i];
                if(c.getName().equals(key)){
                    System.out.println("Cookie值为："+c.getValue());
                    break;
                };
            }
        }else{
            System.out.println("No cookie Existing");
            return null;
        }
        return c.getValue();
    }


    public static HttpServletResponse setCookie(HttpServletResponse response, String cookieName, String value, int expires) {
        try{
            if (StringUtils.isNotBlank(cookieName)) {
                Cookie cookie = new Cookie(cookieName, value);
                cookie.setMaxAge(expires);
                cookie.setDomain("train.online.com");
                cookie.setPath("/");
//                cookie.setSecure(secure);
//                cookie.setHttpOnly(httpOnly);
                response.addCookie(cookie);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

}
