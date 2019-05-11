package com.cn.train.service.impl;

import com.cn.train.dao.HistoryMapper;
import com.cn.train.entity.History;
import com.cn.train.entity.bussiness.HistoryBusiness;
import com.cn.train.entity.bussiness.InformationBusiness;
import com.cn.train.service.HistoryService;
import com.cn.train.service.InformationService;
import com.cn.train.utils.ReturnHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: JiaHao.Kuang
 * @create: 2019-04-25 14:47
 **/
@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    HistoryMapper historyMapper;
    @Autowired
    InformationService informationService;

    @Override
    public Map<String, Object> getHistoryByUid(Integer uid,Integer pageNo, Integer limit) throws Exception {
        Map<String, Object> map;

        int total = historyMapper.getTotalCount(uid);

        PageHelper.startPage(pageNo, limit);

        List<History> existList = historyMapper.getAllHistoryByUid(uid);

        if(null != existList && existList.size() > 0){
            List<HistoryBusiness> infolist = new ArrayList<>();
            for(History history: existList){
                Map<String, Object> map1 = new HashMap<>();
                HistoryBusiness historyBusiness = new HistoryBusiness();

                int infoid = history.getInfoid();
                InformationServiceImpl info = new InformationServiceImpl();
                map1 = informationService.getInformationByInfoid(infoid);
                InformationBusiness informationBusiness = (InformationBusiness)map1.get("info");

                historyBusiness.setInfotitle(informationBusiness.getInfotitle());
                historyBusiness.setInfocontent(informationBusiness.getInfocontent());
                historyBusiness.setCreateusername(informationBusiness.getCreateusername());
                historyBusiness.setCreatetime(informationBusiness.getCreatetime());
                historyBusiness.setStyle(informationBusiness.getStyle());
                historyBusiness.setInfoid(informationBusiness.getInfoid());
                historyBusiness.setIhid(history.getIhid());

                infolist.add(historyBusiness);
            }
            if(infolist.size() > 0){
                map = ReturnHelper.success("success");
                PageInfo<HistoryBusiness> page = new PageInfo<HistoryBusiness>(infolist);
                map.put("data",page.getList());
                map.put("count", total);
            }else {
                map = ReturnHelper.fail("fail");
            }
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteHistortByIhid(Integer ihid) throws Exception {
        Map<String, Object> map;
        int result = historyMapper.deleteByPrimaryKey(ihid);
        if(result > 0){
            map = ReturnHelper.success("success");
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }

    @Override
    public Map<String, Object> addHistoryByinfoId(Integer uid, Integer infoid) throws Exception {
        Map<String, Object> map;
        History history = new History();
        history.setInfoid(infoid);
        history.setUid(uid);
        int result = historyMapper.insertSelective(history);
        if(result > 0){
            map = ReturnHelper.success("success");
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }
}
