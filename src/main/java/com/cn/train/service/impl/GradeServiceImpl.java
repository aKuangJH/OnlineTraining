package com.cn.train.service.impl;

import com.cn.train.dao.AnswerMapper;
import com.cn.train.dao.GradeMapper;
import com.cn.train.dao.TestMapper;
import com.cn.train.dao.UserMapper;
import com.cn.train.entity.Answer;
import com.cn.train.entity.Grade;
import com.cn.train.entity.Test;
import com.cn.train.entity.User;
import com.cn.train.service.GradeService;
import com.cn.train.utils.ReturnHelper;
import com.cn.train.view.GradeViewModel;
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
 * @create: 2019-04-22 16:59
 **/
@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    GradeMapper gradeMapper;

    @Autowired
    TestMapper testMapper;

    @Autowired
    AnswerMapper answerMapper;

    @Autowired
    UserMapper userMapper;
    @Override
    public int insertGrade(Grade grade) throws Exception {
        int result = gradeMapper.insert(grade);
        if(result > 0){
            return 1;
        }else {
            return -1;
        }
    }

    @Override
    public Grade checkGrade(Integer uid, Integer tid) throws Exception {
        Grade grade = gradeMapper.selectByUidAndTid(uid, tid);
        return grade;
    }

    @Override
    public int updateGrade(Grade grade) throws Exception {
        int result = gradeMapper.updateByPrimaryKey(grade);
        if(result > 0){
            // 更新成功
            return 1;
        }else {
            // 更新失败
            return -1;

        }
    }

    @Override
    public Map<String, Object> getGradeByTidAndUid(Integer tid, Integer uid) throws Exception {
        Map<String, Object> map;
        Grade grade = gradeMapper.selectByUidAndTid(uid, tid);

        if(null != grade) {
            GradeViewModel vm = new GradeViewModel();
            vm.setGrade(grade.getGrade());
            Test test = testMapper.selectByPrimaryKey(grade.getTid());
            vm.setTestname(test.getTname());
            Answer answer = answerMapper.selectByTidAndUid(tid,uid);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
            vm.setAnswertime(df.format(answer.getCreatetime()));
            User user = userMapper.selectByPrimaryKey(uid);
            vm.setUsername(user.getUsername());

            map = ReturnHelper.success("success");
            map.put("grade",vm);
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }

    @Override
    public Map<String, Object> getGradeList(Integer uid, Integer pageNo, Integer pageSize) throws Exception {
        Map<String, Object> map;
        PageHelper.startPage(pageNo, pageSize);
        List<Grade> gradelist = gradeMapper.selectAllGrade(uid);
        if(null != gradelist && gradelist.size() > 0){
            PageInfo<Grade> page = new PageInfo<Grade>(gradelist);

            List<GradeViewModel> gradeViewModelList = new ArrayList<>();
            for(int i=0;i<gradelist.size();i++){
                GradeViewModel vm = new GradeViewModel();
                vm.setGrade(gradelist.get(i).getGrade());
                vm.setGid(gradelist.get(i).getGid());
                Test test = testMapper.selectByPrimaryKey(gradelist.get(i).getTid());
                vm.setTestname(test.getTname());
                vm.setTid(test.getTid());
                Answer answer = answerMapper.selectByTidAndUid(gradelist.get(i).getTid(),uid);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
                vm.setAnswertime(df.format(answer.getCreatetime()));
                vm.setAid(answer.getAid());
                User user = userMapper.selectByPrimaryKey(uid);
                vm.setUsername(user.getUsername());

                gradeViewModelList.add(vm);
            }
            map = ReturnHelper.success("success");
            map.put("gradelist",gradeViewModelList);
            map.put("count",page.getTotal());
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }
}
