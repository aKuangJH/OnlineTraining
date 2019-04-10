package com.cn.train.controller;

import com.cn.train.entity.Test;
import com.cn.train.service.TestService;
import com.cn.train.utils.CookieHelper;
import com.cn.train.utils.ReturnHelper;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: JiaHao.Kuang
 * @create: 2019-04-02 11:06
 **/
@RequestMapping(value = "test", method = {RequestMethod.GET,RequestMethod.POST})
@RestController
@Api(value = "test", description = "套题管理")
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping(value = "addtest")
    @ApiOperation(value = "新增套题", httpMethod = "POST", notes = "新增套题")
    public Map<String, Object> addTest(HttpServletRequest request, Test test){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            /**
             * 获取用户id
             */
            if(null == CookieHelper.getCookieValue(request, "uid")){
                map = ReturnHelper.fail("获取不到uid");
                return map;
            }

            int uid = Integer.parseInt(CookieHelper.getCookieValue(request, "uid"));
            test.setUid(uid);
            if(StringUtils.isNotBlank(test.getStatus()) && StringUtils.isNotBlank(test.getTname())){
                test.setCreatetime(new Date());
                if("0".equals(test.getStatus())){
                    test.setStatus("公开");
                }else {
                    test.setStatus("私有");
                }
                map = testService.addTest(test);
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return map;
    }

    @RequestMapping(value = "getalltest")
    @ApiOperation(value = "获取用户所有套题", httpMethod = "POST", notes = "当前用户所有套题")
    public Map<String, Object> getAllTestByUid(HttpServletRequest request, Test test){
        Map<String, Object> map = new HashMap<>();
        try{
            /**
             * 获取用户id
             */
            if(null == CookieHelper.getCookieValue(request, "uid")){
                map = ReturnHelper.fail("获取不到uid");
                return map;
            }

            int uid = Integer.parseInt(CookieHelper.getCookieValue(request, "uid"));
            map = testService.getAllTestByUid(uid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "cookietest")
    public String testCookie (HttpServletRequest request) {
        String aa = CookieHelper.getCookieValue(request,"id");
        return "success";
    }

}
