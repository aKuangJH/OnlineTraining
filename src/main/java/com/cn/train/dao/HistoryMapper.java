package com.cn.train.dao;

import com.cn.train.entity.History;

import java.util.List;

public interface HistoryMapper {
    int deleteByPrimaryKey(Integer ihid);

    int insert(History record);

    int insertSelective(History record);

    History selectByPrimaryKey(Integer ihid);

    int updateByPrimaryKeySelective(History record);

    int updateByPrimaryKey(History record);

    List<History> getAllHistoryByUid(Integer uid);

    int getTotalCount(Integer uid);
}