package com.cn.train.service;

import com.cn.train.entity.PostComment;

import java.util.Map;

public interface PostCommentService {
    Map<String, Object> getAllComments(Integer pageNo, Integer pageSize, Integer infoid) throws Exception;

    Map<String, Object> addComment(PostComment postComment) throws Exception;
}
