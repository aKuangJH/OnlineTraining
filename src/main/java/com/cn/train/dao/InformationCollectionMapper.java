package com.cn.train.dao;

import com.cn.train.entity.InformationCollection;

import java.util.List;

public interface InformationCollectionMapper {
    int deleteByPrimaryKey(Integer icid);

    int insert(InformationCollection record);

    int insertSelective(InformationCollection record);

    InformationCollection selectByPrimaryKey(Integer icid);

    int updateByPrimaryKeySelective(InformationCollection record);

    int updateByPrimaryKey(InformationCollection record);

    List<InformationCollection> selectAllCollectionByUid(Integer uid);
}