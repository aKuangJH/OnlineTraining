package com.cn.train.controller;

import com.cn.train.utils.ReturnHelper;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: JiaHao.Kuang
 * @create: 2019-04-10 11:27
 **/

@RequestMapping(value = "answer", method = {RequestMethod.GET,RequestMethod.POST})
@RestController
@Api(value = "answer", description = "问题管理")
public class AnswerController {

    @RequestMapping(value = "loganswer")
    @ApiOperation(value = "记录用户答案", httpMethod = "POST", notes = "记录答案")
    public Map<String, Object> logAnswer(){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(true){
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return map;
    }



}
