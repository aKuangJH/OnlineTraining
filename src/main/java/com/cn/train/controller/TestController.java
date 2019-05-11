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
import org.springframework.web.bind.annotation.RequestParam;
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
    public Map<String, Object> getAllTestByUid(HttpServletRequest request,@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize){
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
            if(null != pageNo && null != pageSize){
                map = testService.getAllTestByUid(uid,pageNo,pageSize);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "gettotaltest")
    @ApiOperation(value = "所有公开通过审核状态套题", httpMethod = "POST", notes = "所有公开且审核通过状态套题")
    public Map<String, Object> getTotalTest(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        Map<String, Object> map = new HashMap<>();
        try{
            if(null != pageNo && null != pageSize){
                map = testService.getTotalTest(pageNo,pageSize);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "testallcount")
    @ApiOperation(value = "根据状态获取所有套题", httpMethod = "GET", notes = "管理员所有套题管理")
    public Map<String, Object> getAllTest(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestParam("type") Integer type){
        Map<String, Object> map = new HashMap<>();
        try{
            if(null != pageNo && null != pageSize && null != type){
                map = testService.getAllTest(pageNo,pageSize,type);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "gettotalcount")
    @ApiOperation(value = "根据状态获取套题总数", httpMethod = "GET", notes = "对应状态所有套题总数")
    public Map<String, Object> getTotalCount(@RequestParam("type") Integer type){
        Map<String, Object> map = new HashMap<>();
        try{
            if(null != type){
                map = testService.getTotalCount(type);
            }
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

    @RequestMapping(value = "deletetest")
    @ApiOperation(value = "删除套题", httpMethod = "GET", notes = "删除套题")
    public Map<String, Object> deleteTest(@RequestParam("tid") Integer tid){
        Map<String, Object> map = new HashMap<>();
        try{
            if(null != tid){
                map = testService.deleteByTid(tid);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "changetype")
    @ApiOperation(value = "改变审核状态", httpMethod = "GET", notes = "改变审核状态")
    public Map<String, Object> changeTestType(@RequestParam("tid") Integer tid, @RequestParam("type") Integer type){
        Map<String, Object> map = new HashMap<>();
        try{
            if(null != tid && null != type){
                map = testService.changeType(tid,type);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

}
