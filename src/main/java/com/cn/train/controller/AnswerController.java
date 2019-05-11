package com.cn.train.controller;

import com.cn.train.service.AnswerService;
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
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: JiaHao.Kuang
 * @create: 2019-04-10 11:27
 **/

@RequestMapping(value = "answer", method = {RequestMethod.GET,RequestMethod.POST})
@RestController
@Api(value = "answer", description = "问题管理", basePath = "server")
public class AnswerController {

    @Autowired
    AnswerService answerService;


    @RequestMapping(value = "loganswer")
    @ApiOperation(value = "记录用户答案", httpMethod = "POST", notes = "记录答案")
    public Map<String, Object> logAnswer(HttpServletRequest request, @RequestParam("tid") Integer tid, @RequestParam("answer") String answer){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(null != tid && StringUtils.isNotBlank(answer)){

//                if(null == CookieHelper.getCookieValue(request, "uid")){
//                    map = ReturnHelper.fail("获取不到uid");
//                    return map;
//                }
//
//                int uid = Integer.parseInt(CookieHelper.getCookieValue(request, "uid"));
                map = answerService.addAnswer(1, tid, answer);
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return map;
    }


    @RequestMapping(value = "gettestanswer")
    @ApiOperation(value = "获取答题记录", httpMethod = "POST", notes = "获取答题记录")
    public Map<String, Object> getTestAnswer(HttpServletRequest request, @RequestParam("tid") Integer tid, @RequestParam("aid") Integer aid,@RequestParam("gid") Integer gid){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(null != tid && null != aid && null != gid){
                map = answerService.getTestAnswer(tid,aid,gid);
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return map;
    }

    @RequestMapping(value = "test")
    @ApiOperation(value = "测试接口", httpMethod = "POST", notes = "测试接口")
    public Map<String, Object> testAnswer(HttpServletRequest requestid){
        Map<String, Object> map = new HashMap<String, Object>();
        map = ReturnHelper.success("欢迎光临");
        return map;
    }

}
