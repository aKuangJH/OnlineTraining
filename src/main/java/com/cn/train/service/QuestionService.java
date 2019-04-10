package com.cn.train.service;

import com.cn.train.view.bean.QuestionBean;

import java.util.Map;

public interface QuestionService {
    Map<String, Object> addQuestion(QuestionBean questionBean) throws Exception;

    Map<String, Object> showAllQuestion(Integer tid) throws Exception;

    Map<String, Object> deleteQuestion(Integer qid) throws Exception;

    Map<String, Object> updateQuestion(QuestionBean questionBean) throws Exception;
}
