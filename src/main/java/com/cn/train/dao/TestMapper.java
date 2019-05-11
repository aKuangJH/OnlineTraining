package com.cn.train.dao;

import com.cn.train.entity.Test;

import java.util.List;

public interface TestMapper {
    int deleteByPrimaryKey(Integer tid);

    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(Integer tid);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);

    List<Test> getAllTestByUid(Integer uid);

    List<Test> getTotalTest(String status, Integer testauthority);

    List<Test> getAllTestByStatusAndAuthority(String status, Integer testauthority);

    int getCountTestByStatusAndAuthority(String status, Integer testauthority);
}