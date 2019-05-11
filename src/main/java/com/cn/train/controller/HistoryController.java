package com.cn.train.controller;

import com.cn.train.service.HistoryService;
import com.cn.train.utils.CookieHelper;
import com.cn.train.utils.ReturnHelper;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: JiaHao.Kuang
 * @create: 2019-04-25 14:45
 **/
@RequestMapping(value = "history", method = {RequestMethod.GET,RequestMethod.POST})
@RestController
@Api(value = "history", description = "帖子历史浏览管理")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @RequestMapping(value = "getinfohistory")
    @ApiOperation(value = "历史浏览记录", httpMethod = "GET", notes = "历史浏览记录")
    public Map<String, Object> getAllHistoryByUid(HttpServletRequest request, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
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
            if(null != page && null != limit){
                map = historyService.getHistoryByUid(uid,page,limit);
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }

        }catch (Exception e){
            System.out.println(e);
        }

        return map;
    }


    @RequestMapping(value = "deleteinfohistory")
    @ApiOperation(value = "删除历史浏览记录", httpMethod = "GET", notes = "删除历史浏览记录")
    public Map<String, Object> deleteHistoryByIhid(@RequestParam("ihid") Integer ihid){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(null != ihid){
                map = historyService.deleteHistortByIhid(ihid);
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return map;
    }

    @RequestMapping(value = "addinfohistory")
    @ApiOperation(value = "新增历史浏览记录", httpMethod = "GET", notes = "新增历史浏览记录")
    public Map<String, Object> addHistoryByIhid(HttpServletRequest request, @RequestParam("infoid") Integer infoid){
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

            if(null != infoid){
                map = historyService.addHistoryByinfoId(uid, infoid);
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return map;
    }

}
