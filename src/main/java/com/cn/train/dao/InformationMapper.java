package com.cn.train.dao;

import com.cn.train.entity.Information;

import java.util.List;

public interface InformationMapper {
    int deleteByPrimaryKey(Integer infoid);

    int insert(Information record);

    int insertSelective(Information record);

    Information selectByPrimaryKey(Integer infoid);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKeyWithBLOBs(Information record);

    int updateByPrimaryKey(Information record);

    List<Information> getAllInformations();

    int getAllCount();

    List<Information> getInformationsByStyle(Integer style);

}