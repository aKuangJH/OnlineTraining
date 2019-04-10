package com.cn.train.controller;

import com.cn.train.view.bean.QuestionBean;
import com.cn.train.service.QuestionService;
import com.cn.train.utils.ReturnHelper;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: JiaHao.Kuang
 * @create: 2019-04-04 16:11
 **/
@RequestMapping(value = "question", method = {RequestMethod.GET,RequestMethod.POST})
@RestController
@Api(value = "question", description = "问题管理")
public class QuestionController {

    @Autowired
    QuestionService questionService;


    @RequestMapping(value = "showallquestions")
    @ApiOperation(value = "查询套题试题", httpMethod = "POST", notes = "根据套题号查询试题")
    public Map<String, Object> showAllQuestionsByTid(@RequestParam("tid") Integer tid){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(null != tid){
                map = questionService.showAllQuestion(tid);
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return map;
    }


    @RequestMapping(value = "addquestion")
    @ApiOperation(value = "添加问题", httpMethod = "POST", notes = "添加问题")
    public Map<String, Object> addQuestionToTest(QuestionBean questionBean){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(null != questionBean && StringUtils.isNotBlank(questionBean.getQtitle())){
                map = questionService.addQuestion(questionBean);
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return map;
    }


    @RequestMapping(value = "deletequestion")
    @ApiOperation(value = "删除问题", httpMethod = "Get", notes = "删除问题")
    public Map<String, Object> deleteQuestionFromTest(@RequestParam("qid") Integer qid){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(null != qid){
                map = questionService.deleteQuestion(qid);
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return map;
    }

    @RequestMapping(value = "updatequestion")
    @ApiOperation(value = "更新问题", httpMethod = "POST", notes = "更新问题")
    public Map<String, Object> updateQuestion(QuestionBean questionBean){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(null != questionBean && StringUtils.isNotBlank(questionBean.getQtitle())){
                map = questionService.updateQuestion(questionBean);
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return map;
    }
}
