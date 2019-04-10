package com.cn.train.dao;

import com.cn.train.entity.Select;

public interface SelectMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(Select record);

    int insertSelective(Select record);

    Select selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Select record);

    int updateByPrimaryKey(Select record);

    Select selectByQid(Integer qid);

    int updateByQid(Select select);
}