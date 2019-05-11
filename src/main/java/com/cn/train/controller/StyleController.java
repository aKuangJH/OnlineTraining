package com.cn.train.controller;

import com.cn.train.service.StyleService;
import com.cn.train.utils.ReturnHelper;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @description:
 * @author: JiaHao.Kuang
 * @create: 2019-05-04 13:32
 **/
@RequestMapping(value = "style", method = {RequestMethod.GET,RequestMethod.POST})
@RestController
@Api(value = "style", description = "帖子类型管理")
public class StyleController {

    @Autowired
    StyleService styleService;

    @RequestMapping("stylelist")
    @ApiOperation(value = "获取帖子类型", httpMethod = "GET", notes = "获取帖子类型")
    public Map<String, Object> getAllStyle(){
        Map<String, Object> map;
        try {
            map = styleService.getAllStyle();
        }catch (Exception e){
            e.printStackTrace();
            map = ReturnHelper.fail("异常"+e.toString());
        }
        return map;
    }
}
