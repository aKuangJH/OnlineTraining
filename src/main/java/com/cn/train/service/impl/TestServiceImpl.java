package com.cn.train.service.impl;

import com.cn.train.dao.TestMapper;
import com.cn.train.entity.Test;
import com.cn.train.service.TestService;
import com.cn.train.utils.ReturnHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: JiaHao.Kuang
 * @create: 2019-04-02 11:22
 **/
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestMapper testMapper;

    @Override
    public Map<String, Object> addTest(Test test) throws Exception {
        Map<String, Object> map;
        int result = testMapper.insertSelective(test);
        if(result>0){
            map = ReturnHelper.success("success");
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }

    @Override
    public Map<String, Object> getAllTestByUid(Integer uid) throws Exception {
        Map<String, Object> map;
        List<Test> existlist = testMapper.getAllTestByUid(uid);
        if (null != existlist && existlist.size()>0) {
            map = ReturnHelper.success("success");
            map.put("testlist",existlist);
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }
}
