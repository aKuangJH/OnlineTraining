package com.cn.train.dao;

import com.cn.train.entity.PostComment;

import java.util.List;

public interface PostCommentMapper {
    int deleteByPrimaryKey(Integer pcid);

    int insert(PostComment record);

    int insertSelective(PostComment record);

    PostComment selectByPrimaryKey(Integer pcid);

    int updateByPrimaryKeySelective(PostComment record);

    int updateByPrimaryKey(PostComment record);

    List<PostComment> getAllComments(Integer infoid);
}