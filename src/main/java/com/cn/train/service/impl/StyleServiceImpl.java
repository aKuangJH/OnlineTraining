package com.cn.train.service.impl;

import com.cn.train.dao.StyleMapper;
import com.cn.train.entity.Style;
import com.cn.train.service.StyleService;
import com.cn.train.utils.ReturnHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: JiaHao.Kuang
 * @create: 2019-05-04 13:54
 **/
@Service
public class StyleServiceImpl implements StyleService {

    @Autowired
    StyleMapper styleMapper;

    @Override
    public Map<String, Object> getAllStyle() throws Exception {
        Map<String, Object> map;
        List<Style> existlist = styleMapper.getAllStyle();
        if(existlist.size() > 0 && null != existlist){
            map = ReturnHelper.success("success");
            map.put("style",existlist);
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }
}
