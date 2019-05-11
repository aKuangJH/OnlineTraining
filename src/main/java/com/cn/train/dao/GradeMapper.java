package com.cn.train.dao;

import com.cn.train.entity.Grade;

import java.util.List;

public interface GradeMapper {
    int deleteByPrimaryKey(Integer gid);

    int insert(Grade record);

    int insertSelective(Grade record);

    Grade selectByPrimaryKey(Integer gid);

    int updateByPrimaryKeySelective(Grade record);

    int updateByPrimaryKey(Grade record);

    Grade selectByUidAndTid(Integer uid, Integer tid);

    List<Grade> selectAllGrade(Integer uid);
}