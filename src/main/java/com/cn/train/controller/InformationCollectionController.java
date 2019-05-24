package com.cn.train.controller;

import com.cn.train.service.InformationCollectionService;
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
 * @create: 2019-05-12 01:08
 **/
@RequestMapping(value = "informationcollection", method = {RequestMethod.GET,RequestMethod.POST})
@RestController
@Api(value = "informationcollection", description = "帖子收藏管理")
public class InformationCollectionController {

    @Autowired
    InformationCollectionService informationCollectionService;


    @RequestMapping(value = "collectinformation")
    @ApiOperation(value = "收藏帖子", httpMethod = "GET", notes = "收藏帖子")
    public Map<String, Object> collectInformation(HttpServletRequest request, @RequestParam("infoid") Integer infoid){
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
                map = informationCollectionService.collectInformation(uid,infoid);
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return map;
    }

    @RequestMapping(value = "cancleinformationcollection")
    @ApiOperation(value = "帖子取消收藏", httpMethod = "GET", notes = "帖子取消收藏")
    public Map<String, Object> cancleInformationCollection(@RequestParam("icid") Integer icid){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(null != icid){
                map = informationCollectionService.cancleInformationCollection(icid);
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return map;
    }


    @RequestMapping(value = "showallinformationcollection")
    @ApiOperation(value = "显示所有收藏帖子", httpMethod = "GET", notes = "显示所有收藏帖子")
    public Map<String, Object> cancleInformationCollection(HttpServletRequest request,@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
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
            if(null != pageNo && null != pageSize){
                map = informationCollectionService.showAllInformationCollection(uid,pageNo,pageSize);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return map;
    }

}
