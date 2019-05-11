package com.cn.train.service;

import java.util.Map;

public interface AnswerService {
    Map<String, Object> addAnswer(Integer uid, Integer tid, String answer) throws Exception;

    Map<String, Object> getTestAnswer(Integer tid, Integer aid, Integer gid) throws Exception;
}
