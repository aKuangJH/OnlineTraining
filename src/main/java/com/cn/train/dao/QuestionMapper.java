package com.cn.train.dao;

import com.cn.train.entity.Question;

import java.util.List;

public interface QuestionMapper {
    int deleteByPrimaryKey(Integer qid);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer qid);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

    List<Question> selectByTid(Integer tid);

}