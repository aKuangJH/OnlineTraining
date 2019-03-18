package com.cn.train.utils;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieHelper {

    public String getCookieValue(String key){
        Cookie[]cookies = WebContextHelper.getRequest().getCookies();
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
}
