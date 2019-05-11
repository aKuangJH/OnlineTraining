package com.cn.train.controller;

import com.cn.train.service.GradeService;
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
 * @create: 2019-04-23 10:39
 **/

@RequestMapping(value = "grade", method = {RequestMethod.GET,RequestMethod.POST})
@RestController
@Api(value = "grade", description = "成绩管理", basePath = "server")
public class GradeController {

    @Autowired
    GradeService gradeService;

    @RequestMapping(value = "getgrade")
    @ApiOperation(value = "获取套题成绩", httpMethod = "GET", notes = "获取套题成绩")
    public Map<String, Object> getGradeByTidAndUid(HttpServletRequest request, @RequestParam("tid") Integer tid){
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
            if(null != tid){
                map = gradeService.getGradeByTidAndUid(tid,uid);
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return map;
    }

    @RequestMapping(value = "getgradehistory")
    @ApiOperation(value = "获取答题记录", httpMethod = "GET", notes = "获取答题记录成绩")
    public Map<String, Object> getGradeList(HttpServletRequest request,@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
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
                map = gradeService.getGradeList(uid,pageNo,pageSize);
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return map;
    }

}
