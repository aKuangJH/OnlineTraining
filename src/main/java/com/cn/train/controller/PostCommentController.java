package com.cn.train.controller;

import com.cn.train.entity.PostComment;
import com.cn.train.service.PostCommentService;
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
 * @create: 2019-05-08 16:35
 **/
@RequestMapping(value = "postcomment", method = {RequestMethod.GET,RequestMethod.POST})
@RestController
@Api(value = "postcomment", description = "帖子评论")
public class PostCommentController {

    @Autowired
    PostCommentService postCommentService;

    @RequestMapping(value = "getpostcomment")
    @ApiOperation(value = "获取帖子评论", httpMethod = "GET", notes = "获取帖子评论")
    public Map<String, Object> getPostComments(HttpServletRequest request, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestParam("infoid") Integer infoid){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(null != pageNo && null != pageSize && null != infoid){
                map = postCommentService.getAllComments(pageNo,pageSize,infoid);
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return map;
    }

    @RequestMapping(value = "addcomment")
    @ApiOperation(value = "新增帖子评论", httpMethod = "GET", notes = "新增帖子评论")
    public Map<String, Object> addPostComments(HttpServletRequest request, PostComment postComment) {
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
            if(null != postComment){
                postComment.setUid(uid);
                postComment.setCreatetime(new Date());
                map = postCommentService.addComment(postComment);
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return map;
    }

}
