package com.cn.train.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.train.dao.AnswerMapper;
import com.cn.train.dao.GradeMapper;
import com.cn.train.dao.QuestionMapper;
import com.cn.train.entity.Answer;
import com.cn.train.entity.Grade;
import com.cn.train.entity.Question;
import com.cn.train.entity.bussiness.QuestionBusiness;
import com.cn.train.service.AnswerService;
import com.cn.train.service.GradeService;
import com.cn.train.service.QuestionService;
import com.cn.train.utils.ReturnHelper;
import com.cn.train.view.AnswerViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: JiaHao.Kuang
 * @create: 2019-04-10 12:56
 **/
@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    AnswerMapper answerMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    QuestionService questionService;
    @Autowired
    GradeService gradeService;
    @Autowired
    GradeMapper gradeMapper;

    @Transactional
    @Override
    public Map<String, Object> addAnswer(Integer uid, Integer tid, String answer) throws Exception {
        Map<String, Object> map;

        Answer useranswer = new Answer();
        useranswer.setUid(uid);
        useranswer.setTid(tid);
        useranswer.setAnswer(answer);
        useranswer.setCreatetime(new Date());

        Answer existanswer = answerMapper.selectByTidAndUid(tid,uid);
        if(null != existanswer){
            useranswer.setAid(existanswer.getAid());
            // 曾经做过此套试题，更新答案
            int updateresult = answerMapper.updateByPrimaryKey(useranswer);
            int result2 = setGrade(tid, answer, uid);

            if(updateresult > 0 && result2 >0){
                map = ReturnHelper.success("success");
            }else {
                map = ReturnHelper.fail("fail");
            }
        }else {
            int result = answerMapper.insert(useranswer);
            int result2 = setGrade(tid, answer, uid);

            if(result > 0 && result2 >0){
                map = ReturnHelper.success("success");
            }else {
                map = ReturnHelper.fail("fail");
            }
        }
        return map;
    }

    @Override
    public Map<String, Object> getTestAnswer(Integer tid, Integer aid, Integer gid) throws Exception {
        Map<String, Object> map;
        AnswerViewModel vm = new AnswerViewModel();
        List<Question> questions = questionMapper.selectByTid(tid);
        if(null != questions && questions.size() > 0){
            Map<String, String> rightAnswerMap = new HashMap<String,String>();

            for(int i=0;i<questions.size();i++){
                rightAnswerMap.put(String.valueOf(i),questions.get(i).getQanswer());
            }
            String rightanswer = JSONObject.toJSONString(rightAnswerMap);

            Answer answer = answerMapper.selectByPrimaryKey(aid);
            if(null != answer){
                String uanswer = answer.getAnswer();

                Grade grade = gradeMapper.selectByPrimaryKey(gid);
                if(null != grade){
                    vm.setUanswer(uanswer);
                    vm.setRanswer(rightanswer);

                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
                    vm.setCreatetime(df.format(answer.getCreatetime()));

                    vm.setGrade(grade.getGrade());

                    map = ReturnHelper.success("success");
                    map.put("answer",vm);
                }else {
                    map = ReturnHelper.fail("fail，没找到对应成绩记录");
                }

            }else {
                map = ReturnHelper.fail("fail，没找到对应答题记录");
            }
        }else {
            map = ReturnHelper.fail("fail，没找到对应试题");
        }
        return map;
    }


    public int setGrade(Integer tid, String answer, Integer uid){
        Map<String, Object> map;
        int count = 100;
        try {
            map = questionService.showAllQuestion(tid);
            if("0".equals(map.get("code").toString())){

                JSONObject obj = JSON.parseObject(answer);

                List<QuestionBusiness> questionBussinesseslist = (List) map.get("questionlist");

                for(int i=0;i<questionBussinesseslist.size();i++){
                    String uanswer = obj.getString(""+i+"");
                    String ranswer = questionBussinesseslist.get(i).getQanswer();
                    if(!uanswer.equals(ranswer)){
                        count = count - 10 ;
//                        count -= 10;
                    }
                }

                Grade grade = new Grade();
                grade.setGrade(count);
                grade.setTid(tid);
                grade.setUid(uid);
                // 检查是否已有成绩
                Grade checkGrade = gradeService.checkGrade(uid, tid);

                if(null != checkGrade){
                    // 有成绩，更新
                    grade.setGid(checkGrade.getGid());
                    int result = gradeService.updateGrade(grade);
                    if(result > 0){
                        // 更新成绩成功
                        return 1;
                    }else {
                        // 更新成绩失败
                        return -1;
                    }

                }else {
                    // 无成绩，新增记录
                    int result = gradeService.insertGrade(grade);
                    if(result > 0){
                        // 插入成绩成功
                        return 1;
                    }else {
                        // 插入成绩失败
                        return -1;
                    }
                }

            }else {
                // 获取所有该套题所有问题失败
                return -1;
            }

        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
