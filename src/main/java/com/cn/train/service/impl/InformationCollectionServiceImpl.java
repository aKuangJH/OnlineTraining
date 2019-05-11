package com.cn.train.service.impl;

import com.cn.train.dao.InformationCollectionMapper;
import com.cn.train.dao.InformationMapper;
import com.cn.train.entity.Information;
import com.cn.train.entity.InformationCollection;
import com.cn.train.entity.bussiness.InformationCollectionBusiness;
import com.cn.train.service.InformationCollectionService;
import com.cn.train.utils.ReturnHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: JiaHao.Kuang
 * @create: 2019-05-12 01:25
 **/
@Service
public class InformationCollectionServiceImpl implements InformationCollectionService {

    @Autowired
    InformationCollectionMapper informationCollectionMapper;
    @Autowired
    InformationMapper informationMapper;
    @Override
    public Map<String, Object> collectInformation(Integer uid, Integer infoid) throws Exception {
        Map<String, Object> map;
        InformationCollection informationCollection = new InformationCollection();
        informationCollection.setInfoid(infoid);
        informationCollection.setUid(uid);
        informationCollection.setCollecttime(new Date());
        int result = informationCollectionMapper.insert(informationCollection);
        if(result > 0){
            map = ReturnHelper.success("success");
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }

    @Override
    public Map<String, Object> cancleInformationCollection(Integer icid) throws Exception {
        Map<String, Object> map;
        int result = informationCollectionMapper.deleteByPrimaryKey(icid);
        if(result > 0){
            map = ReturnHelper.success("success");
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }

    @Override
    public Map<String, Object> showAllInformationCollection(Integer uid) throws Exception {
        Map<String, Object> map;
        List<InformationCollection> existlist = informationCollectionMapper.selectAllCollectionByUid(uid);
        if(null != existlist && existlist.size() > 0){
            List<InformationCollectionBusiness> infolist = new ArrayList<>();
            for(int i=0;i<existlist.size();i++){
                InformationCollectionBusiness informationCollectionBusiness = new InformationCollectionBusiness();

                informationCollectionBusiness.setInfoid(existlist.get(i).getInfoid());
                Information information = informationMapper.selectByPrimaryKey(existlist.get(i).getInfoid());
                informationCollectionBusiness.setInfotitle(information.getInfotitle());
                informationCollectionBusiness.setIcid(existlist.get(i).getIcid());

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                informationCollectionBusiness.setCollecttime(df.format(existlist.get(i).getCollecttime()));

                infolist.add(informationCollectionBusiness);
            }
            map = ReturnHelper.success("success");
            map.put("infolist",infolist);

        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }
}
