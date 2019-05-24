package com.cn.train.dao;

import com.cn.train.entity.QuestionCollection;

import java.util.List;

public interface QuestionCollectionMapper {
    int deleteByPrimaryKey(Integer qcid);

    int insert(QuestionCollection record);

    int insertSelective(QuestionCollection record);

    QuestionCollection selectByPrimaryKey(Integer qcid);

    int updateByPrimaryKeySelective(QuestionCollection record);

    int updateByPrimaryKey(QuestionCollection record);

    List<QuestionCollection> getAllQuestionCollection(Integer uid);

    int deleteByUidAndQid(Integer uid, Integer qid);

    QuestionCollection queryCollection(Integer uid, Integer qid);
}