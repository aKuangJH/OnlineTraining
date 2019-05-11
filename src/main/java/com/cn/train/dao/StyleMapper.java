package com.cn.train.dao;

import com.cn.train.entity.Style;

import java.util.List;

public interface StyleMapper {
    int deleteByPrimaryKey(Integer istyid);

    int insert(Style record);

    int insertSelective(Style record);

    Style selectByPrimaryKey(Integer istyid);

    int updateByPrimaryKeySelective(Style record);

    int updateByPrimaryKey(Style record);

    List<Style> getAllStyle();
}