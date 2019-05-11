package com.cn.train.service;

import java.util.Map;

public interface QuestionCollectionService {
    Map<String, Object> showAllQuestions(Integer uid) throws Exception;

    Map<String, Object> cancleCollectQuestion(Integer uid, Integer qid) throws Exception;

    Map<String, Object> collectQuestion(Integer uid, Integer qid) throws Exception;
}
