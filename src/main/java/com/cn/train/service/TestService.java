package com.cn.train.service;

import com.cn.train.entity.Test;

import java.util.Map;

public interface TestService {
    Map<String, Object> addTest(Test test) throws Exception;

    Map<String, Object> getAllTestByUid(Integer uid) throws  Exception;
}
