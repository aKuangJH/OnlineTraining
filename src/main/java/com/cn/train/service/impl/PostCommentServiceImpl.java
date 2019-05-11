package com.cn.train.service.impl;

import com.cn.train.dao.PostCommentMapper;
import com.cn.train.dao.UserMapper;
import com.cn.train.entity.PostComment;
import com.cn.train.entity.User;
import com.cn.train.entity.bussiness.PostCommentBusiness;
import com.cn.train.service.PostCommentService;
import com.cn.train.utils.ReturnHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: JiaHao.Kuang
 * @create: 2019-05-08 16:44
 **/
@Service
public class PostCommentServiceImpl implements PostCommentService {

    @Autowired
    PostCommentMapper postCommentMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public Map<String, Object> getAllComments(Integer pageNo, Integer pageSize, Integer infoid) throws Exception {
        Map<String, Object> map;

        PageHelper.startPage(pageNo, pageSize);

        List<PostComment> existlist = postCommentMapper.getAllComments(infoid);

        if (null != existlist && existlist.size() > 0){
            PageInfo<PostComment> page = new PageInfo<PostComment>(existlist);

            List<PostCommentBusiness> commentlist = new ArrayList<>();

            for(int i=0;i<existlist.size();i++){
                PostCommentBusiness postCommentBusiness = new PostCommentBusiness();
                PostComment postComment = existlist.get(i);
                postCommentBusiness.setContent(postComment.getContent());
                postCommentBusiness.setPcid(postComment.getPcid());

                User user = userMapper.selectByPrimaryKey(postComment.getUid());
                postCommentBusiness.setUser(user);

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                postCommentBusiness.setCreatetime(df.format(postComment.getCreatetime()));

                commentlist.add(postCommentBusiness);
            }
            map = ReturnHelper.success("success");
            map.put("comments",commentlist);
            map.put("count",page.getTotal());

        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }

    @Override
    public Map<String, Object> addComment(PostComment postComment) throws Exception {
        Map<String, Object> map;

        int result = postCommentMapper.insertSelective(postComment);
        if(result > 0){
            map = ReturnHelper.success("success");
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }
}
