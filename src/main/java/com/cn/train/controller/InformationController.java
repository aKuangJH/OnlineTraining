package com.cn.train.controller;

import com.cn.train.entity.Information;
import com.cn.train.service.InformationService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: JiaHao.Kuang
 * @create: 2019-04-25 15:05
 **/
@RequestMapping(value = "information", method = {RequestMethod.GET,RequestMethod.POST})
@RestController
@Api(value = "information", description = "帖子管理")
public class InformationController {

    @Autowired
    InformationService informationService;

    @RequestMapping(value = "getinformationdetail")
    @ApiOperation(value = "获取帖子详情", httpMethod = "GET", notes = "获取帖子详情")
    public Map<String, Object> getInformationDetail(HttpServletRequest request, @RequestParam("infoid") Integer infoid){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(null != infoid){
                map = informationService.getInformationByInfoid(infoid);
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return map;
    }

    @RequestMapping(value = "getallinformationlist")
    @ApiOperation(value = "获取所有帖子信息", httpMethod = "GET", notes = "获取所有帖子信息")
    public Map<String, Object> getAllInformationList(HttpServletRequest request, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
//            if(null != page && limit != limit){
                map = informationService.getAllInformationList(page, limit);
//            }else {
//                map = ReturnHelper.fail("参数校验失败");
//            }
        }catch (Exception e){
            System.out.println(e);
        }
        return map;
    }

    @RequestMapping(value = "getinformationbystyle")
    @ApiOperation(value = "根据类型获取帖子信息", httpMethod = "GET", notes = "根据类型获取帖子信息")
    public Map<String, Object> getAllInformationByStyle(HttpServletRequest request, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit,@RequestParam("style") Integer style){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
//            if(null != page && null != limit && null != style){
            map = informationService.getAllInformationByStyle(page, limit, style);
//            }else {
//                map = ReturnHelper.fail("参数校验失败");
//            }
        }catch (Exception e){
            System.out.println(e);
        }
        return map;
    }


    @RequestMapping(value = "deleteinformation")
    @ApiOperation(value = "删除帖子信息", httpMethod = "GET", notes = "删除帖子信息")
    public Map<String, Object> deleteInformationByInfoid(HttpServletRequest request, @RequestParam("infoid") Integer infoid){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(null != infoid){
                map = informationService.deleteInformationByInfoId(infoid);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return map;
    }

    @RequestMapping(value = "addinformation")
    @ApiOperation(value = "新增帖子", httpMethod = "GET", notes = "新增帖子")
    public Map<String, Object> addInformation(HttpServletRequest request, Information info){
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
            if(null != info.getInfotitle() && null != info.getInfocontent()){
                info.setUid(uid);
                info.setCreatetime(new Date());
                map = informationService.addIInformation(info);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return map;
    }

}
