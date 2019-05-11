package com.cn.train.service;

import com.cn.train.entity.Test;

import java.util.Map;

public interface TestService {
    Map<String, Object> addTest(Test test) throws Exception;

    Map<String, Object> getAllTestByUid(Integer uid,Integer pageNo, Integer pageSize) throws  Exception;

    Map<String, Object> getTotalTest(Integer pageNo, Integer pageSize) throws Exception;

    Map<String, Object> getAllTest(Integer pageNo, Integer pageSize, Integer type) throws Exception;

    Map<String, Object> getTotalCount(Integer type) throws Exception;

    Map<String, Object> deleteByTid(Integer tid) throws Exception;

    Map<String, Object> changeType(Integer tid, Integer type) throws Exception;
}
