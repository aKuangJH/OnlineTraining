package com.cn.train.controller;

import com.cn.train.service.QuestionCollectionService;
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
 * @create: 2019-04-22 10:40
 **/
@RequestMapping(value = "questioncollection", method = {RequestMethod.GET,RequestMethod.POST})
@RestController
@Api(value = "questioncollection", description = "试题收藏")
public class QuestionCollectionController {

    @Autowired
    QuestionCollectionService questionCollectionService;

    @RequestMapping(value = "showallqcollection")
    @ApiOperation(value = "查询所有收藏试题", httpMethod = "GET", notes = "所有收藏试题")
    public Map<String, Object> showAllQuestions(HttpServletRequest request){
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

            map = questionCollectionService.showAllQuestions(uid);
        }catch (Exception e){
            System.out.println(e);
        }

        return map;
    }


    @RequestMapping(value = "canclecollect")
    @ApiOperation(value = "取消收藏", httpMethod = "GET", notes = "取消收藏")
    public Map<String, Object> cancleCollectQuestion(HttpServletRequest request, @RequestParam("qid") Integer qid){
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
            if(null != qid){
                map = questionCollectionService.cancleCollectQuestion(uid, qid);
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return map;
    }

    @RequestMapping(value = "collectquestion")
    @ApiOperation(value = "添加收藏", httpMethod = "GET", notes = "添加收藏")
    public Map<String, Object> CollectQuestion(HttpServletRequest request, @RequestParam("qid") Integer qid){
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
            if(null != qid){
                map = questionCollectionService.collectQuestion(uid, qid);
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return map;
    }

}
