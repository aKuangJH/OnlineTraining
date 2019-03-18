package com.cn.train.utils;

import java.util.HashMap;
import java.util.Map;

public class ReturnHelper {

    /**
     * @author CoolEnoughSmile
     * @function 生成请求成功的基础map对象
     * @param msg 消息
     * @return
     */
    public static Map<String,Object> success(String msg){
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("msg",msg);
        return map;
    }

    /**
     * @author CoolEnoughSmile
     * @function 生成请求失败的基础map对象
     * @param msg 消息
     * @return
     */
    public static Map<String,Object> fail(String msg){
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg",msg);
        return map;
    }
}