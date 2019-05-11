package com.cn.train.service;

import com.cn.train.entity.Information;

import java.util.Map;

/**
 * @description:
 * @author: JiaHao.Kuang
 * @create: 2019-04-25 15:09
 **/
public interface InformationService {
    Map<String, Object> getInformationByInfoid(Integer infoid) throws Exception;

    Map<String, Object> getAllInformationList(Integer page, Integer limit) throws Exception;

    Map<String, Object> deleteInformationByInfoId(Integer infoid) throws Exception;

    Map<String, Object> getAllInformationByStyle(Integer page, Integer limit, Integer style) throws Exception;

    Map<String, Object> addIInformation(Information info);
}
