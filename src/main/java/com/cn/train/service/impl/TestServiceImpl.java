package com.cn.train.service.impl;

import com.cn.train.dao.AnswerMapper;
import com.cn.train.dao.TestMapper;
import com.cn.train.dao.UserMapper;
import com.cn.train.entity.Test;
import com.cn.train.entity.User;
import com.cn.train.entity.bussiness.TestBusiness;
import com.cn.train.service.TestService;
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
 * @create: 2019-04-02 11:22
 **/
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestMapper testMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AnswerMapper answerMapper;

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
    public Map<String, Object> getAllTestByUid(Integer uid,Integer pageNo, Integer pageSize) throws Exception {
        Map<String, Object> map;
        PageHelper.startPage(pageNo, pageSize);
        List<Test> existlist = testMapper.getAllTestByUid(uid);
        if (null != existlist && existlist.size()>0) {
            map = ReturnHelper.success("success");
            PageInfo<Test> page = new PageInfo<Test>(existlist);
            map.put("testlist",existlist);
            map.put("count", page.getTotal());
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }

    @Override
    public Map<String, Object> getTotalTest(Integer pageNo, Integer pageSize) throws Exception {
        Map<String, Object> map;
        PageHelper.startPage(pageNo, pageSize);
        String status = "公开";
        //0：未审核 1：通过审核
        Integer testauthority = 1;
        List<Test> existlist = testMapper.getTotalTest(status, testauthority);
        if (null != existlist && existlist.size()>0) {
            PageInfo<Test> page = new PageInfo<Test>(existlist);

            List<TestBusiness> testlist = new ArrayList<>();

            for(int i=0;i<existlist.size();i++){

                Test test = existlist.get(i);
                TestBusiness testBusiness = new TestBusiness();
                int uid = test.getUid();
                User user = userMapper.selectByPrimaryKey(uid);

                testBusiness.setTid(test.getTid());
                testBusiness.setTname(test.getTname());
                testBusiness.setTestauthority(test.getTestauthority());

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                testBusiness.setCreatetime(df.format(test.getCreatetime()));
                testBusiness.setStatus(test.getStatus());
                testBusiness.setUsername(user.getUsername());

                int count = answerMapper.getTestAnswerCount(test.getTid());
                //乘23意义在于返回一个较大数值代表热度
                testBusiness.setAnswercount(count*23);

                testlist.add(testBusiness);
            }
            map = ReturnHelper.success("success");
            map.put("testlist",testlist);
            map.put("count",page.getTotal());
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }

    @Override
    public Map<String, Object> getAllTest(Integer pageNo, Integer pageSize,Integer type) throws Exception {
        Map<String, Object> map;

        // 根据类型获取对应数据
        Type obj = getType(type);

        PageHelper.startPage(pageNo, pageSize);
        List<Test> existlist = testMapper.getAllTestByStatusAndAuthority(obj.getStatus(), obj.getTestauthority());

        if(null != existlist && existlist.size()>0){
            PageInfo<Test> page = new PageInfo<Test>(existlist);

            List<TestBusiness> testlist = new ArrayList<>();

            for(int i=0;i<existlist.size();i++){
                Test test = existlist.get(i);
                TestBusiness testBusiness = new TestBusiness();
                int uid = test.getUid();
                User user = userMapper.selectByPrimaryKey(uid);

                testBusiness.setTid(test.getTid());
                testBusiness.setTname(test.getTname());
                testBusiness.setTestauthority(test.getTestauthority());

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                testBusiness.setCreatetime(df.format(test.getCreatetime()));
                testBusiness.setStatus(test.getStatus());
                testBusiness.setUsername(user.getUsername());

                int count = answerMapper.getTestAnswerCount(test.getTid());
                testBusiness.setAnswercount(count);

                testlist.add(testBusiness);
            }

            map = ReturnHelper.success("success");
            map.put("testlist", testlist);
            map.put("count", page.getTotal());
        }else {
            map = ReturnHelper.fail("fail");

        }
        return map;
    }


    @Override
    public Map<String, Object> getTotalCount(Integer type) throws Exception {
        Map<String, Object> map;
        // 根据类型获取对应数据
        Type obj = getType(type);

        // 获取数据总数
        int totalCount = testMapper.getCountTestByStatusAndAuthority(obj.getStatus(), obj.getTestauthority());
        if(totalCount >= 0){
            map = ReturnHelper.success("success");
            map.put("totalCount",totalCount);
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteByTid(Integer tid) throws Exception {
        Map<String, Object> map;
        int result = testMapper.deleteByPrimaryKey(tid);
        if(result > 0){
            map = ReturnHelper.success("success");
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }

    @Override
    public Map<String, Object> changeType(Integer tid, Integer type) throws Exception {
        Map<String, Object> map;
        Test test = new Test();
        test.setTestauthority(type);
        test.setTid(tid);
        int result = testMapper.updateByPrimaryKeySelective(test);
        if(result > 0){
            map = ReturnHelper.success("success");
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }

    //type 0:所有不论状态 1:所有未审核 2:所有已审核 3:所有公开已审核
    public Type getType(Integer type){
        Type obj = new Type();
        if(type == 1){
            obj.setTestauthority(0);
        }else if(type == 2){
            obj.setTestauthority(1);
        }
        //        if(sta == 0){
//            status = "公开";
//        }else {
//            status = "私有";
//        }
        return obj;
    }

    class Type{
        private String status = "";
        private Integer testauthority;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Integer getTestauthority() {
            return testauthority;
        }

        public void setTestauthority(Integer testauthority) {
            this.testauthority = testauthority;
        }
    }
}
