package com.cn.train.service;

import com.cn.train.entity.Grade;

import java.util.Map;

public interface GradeService {

    int insertGrade(Grade grade) throws Exception;

    Grade checkGrade(Integer uid, Integer tid) throws Exception;

    int updateGrade(Grade grade) throws Exception;

    Map<String, Object> getGradeByTidAndUid(Integer tid, Integer uid) throws Exception;

    Map<String, Object> getGradeList(Integer uid, Integer pageNo, Integer pageSize) throws Exception;
}
