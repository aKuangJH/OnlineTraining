package com.cn.train.service;

import java.util.Map;

public interface InformationCollectionService {
    Map<String, Object> collectInformation(Integer uid, Integer infoid) throws Exception;

    Map<String, Object> cancleInformationCollection(Integer icid) throws Exception;

    Map<String, Object> showAllInformationCollection(Integer uid,Integer pageNo, Integer pageSize) throws Exception;
}
