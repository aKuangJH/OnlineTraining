package com.cn.train.service;

import java.util.Map;

public interface HistoryService {
    Map<String, Object> getHistoryByUid(Integer uid, Integer page, Integer limit) throws Exception;

    Map<String, Object> deleteHistortByIhid(Integer ihid) throws Exception;

    Map<String, Object> addHistoryByinfoId(Integer uid, Integer infoid) throws Exception;
}
