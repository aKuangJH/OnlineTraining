package com.cn.train.service.impl;

import com.cn.train.dao.InformationMapper;
import com.cn.train.dao.StyleMapper;
import com.cn.train.dao.UserMapper;
import com.cn.train.entity.Information;
import com.cn.train.entity.Style;
import com.cn.train.entity.User;
import com.cn.train.entity.bussiness.InformationBusiness;
import com.cn.train.service.InformationService;
import com.cn.train.utils.ReturnHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: JiaHao.Kuang
 * @create: 2019-04-25 15:10
 **/
@Service
public class InformationServiceImpl implements InformationService {

    @Autowired
    InformationMapper informationMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    StyleMapper styleMapper;

    @Override
    public Map<String, Object> getInformationByInfoid(Integer infoid) throws Exception {
        Map<String, Object> map;
        InformationBusiness informationBusiness = getInformation(infoid);
        if(null != informationBusiness){
            map = ReturnHelper.success("success");
            map.put("info", informationBusiness);
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }

    @Override
    public Map<String, Object> getAllInformationList(Integer pageNo, Integer limit) throws Exception {
        Map<String, Object> map;

        int count = informationMapper.getAllCount();

        PageHelper.startPage(pageNo, limit);

        List<Information> existlist = informationMapper.getAllInformations();
        if(null != existlist && existlist.size() > 0){
            List<InformationBusiness> infolist = new ArrayList<>();
            for(int i=0;i<existlist.size();i++){
                InformationBusiness informationBusiness = new InformationBusiness();
                Information information = existlist.get(i);

                informationBusiness.setInfoid(information.getInfoid());
                informationBusiness.setInfotitle(information.getInfotitle());
                informationBusiness.setInfocontent(information.getInfocontent());

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                informationBusiness.setCreatetime(df.format(information.getCreatetime()));

                // 获取创建者用户名
                User user = userMapper.selectByPrimaryKey(information.getUid());
                informationBusiness.setCreateusername(user.getUsername());

                // 获取类型
                Style style = styleMapper.selectByPrimaryKey(information.getIstyid());
                informationBusiness.setStyle(style.getStyle());

                infolist.add(informationBusiness);
            }

            PageInfo<InformationBusiness> page = new PageInfo<InformationBusiness>(infolist);

            map = ReturnHelper.success("success");
            map.put("data",page.getList());
            map.put("count", count);
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteInformationByInfoId(Integer infoid) throws Exception {
        Map<String, Object> map;
        int result = informationMapper.deleteByPrimaryKey(infoid);
        if(result > 0){
            map = ReturnHelper.success("success");
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }

    @Override
    public Map<String, Object> getAllInformationByStyle(Integer pageNo, Integer limit, Integer style) throws Exception {
        Map<String, Object> map;

        PageHelper.startPage(pageNo, limit);
        List<Information> existlist = informationMapper.getInformationsByStyle(style);

        if(null != existlist && existlist.size() > 0){
            PageInfo<Information> page = new PageInfo<Information>(existlist);
            List<InformationBusiness> infolist = new ArrayList<>();
            for(int i=0;i<existlist.size();i++){
                InformationBusiness informationBusiness = new InformationBusiness();
                Information information = existlist.get(i);

                informationBusiness.setInfoid(information.getInfoid());
                informationBusiness.setInfotitle(information.getInfotitle());
                informationBusiness.setInfocontent(information.getInfocontent());

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                informationBusiness.setCreatetime(df.format(information.getCreatetime()));

                // 获取创建者用户名
                User user = userMapper.selectByPrimaryKey(information.getUid());
                informationBusiness.setCreateusername(user.getUsername());

                // 获取类型
//                Style style = styleMapper.selectByPrimaryKey(information.getIstyid());
                informationBusiness.setStyle(information.getIstyid().toString());

                infolist.add(informationBusiness);
            }
            map = ReturnHelper.success("success");
            map.put("data",infolist);
            map.put("count", page.getTotal());
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }

    @Override
    public Map<String, Object> addIInformation(Information info) {
        Map<String, Object> map;
        int result = informationMapper.insertSelective(info);
        if(result > 0){
            map = ReturnHelper.success("success");
            map.put("infoid", info.getInfoid());
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }

    public InformationBusiness getInformation(Integer infoid){

        Information information = informationMapper.selectByPrimaryKey(infoid);

        if(null != information){
            InformationBusiness informationBusiness = new InformationBusiness();

            informationBusiness.setInfoid(information.getInfoid());
            informationBusiness.setInfotitle(information.getInfotitle());
            informationBusiness.setInfocontent(information.getInfocontent());

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            informationBusiness.setCreatetime(df.format(information.getCreatetime()));

            // 获取创建者用户名
            User user = userMapper.selectByPrimaryKey(information.getUid());
            informationBusiness.setCreateusername(user.getUsername());

            // 获取类型
            Style style = styleMapper.selectByPrimaryKey(information.getIstyid());
            informationBusiness.setStyle(style.getStyle());

            return informationBusiness;
        }
        return null;
    }

}
