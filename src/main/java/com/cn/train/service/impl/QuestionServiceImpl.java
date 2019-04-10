package com.cn.train.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.train.dao.QuestionMapper;
import com.cn.train.dao.SelectMapper;
import com.cn.train.entity.Question;
import com.cn.train.entity.Select;
import com.cn.train.entity.bussiness.Options;
import com.cn.train.entity.bussiness.QuestionBussiness;
import com.cn.train.service.QuestionService;
import com.cn.train.utils.ReturnHelper;
import com.cn.train.view.bean.QuestionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: JiaHao.Kuang
 * @create: 2019-04-08 09:29
 **/
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    SelectMapper selectMapper;

    @Transactional
    @Override
    public Map<String, Object> addQuestion(QuestionBean questionBean) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if(questionBean.getQstyle() == 0){
        //选择题
            Question question = new Question();
            question.setTid(questionBean.getTid());
            question.setQtitle(questionBean.getQtitle());
            question.setQstyle(questionBean.getQstyle());
            question.setQanswer(questionBean.getQanswer());
            //返回自增主键
            int result = questionMapper.insert(question);

            if(result > 0){
                Select select = new Select();
                select.setQid(question.getQid());
                select.setOptions(questionBean.getOptions());

                int result2 = selectMapper.insert(select);

                if(result2 > 0){
                    map = ReturnHelper.success("success");
                }else {
                    map = ReturnHelper.fail("select insert fail");
                }
            }else {
                map = ReturnHelper.fail("question insert fail");
            }

        }else if(questionBean.getQstyle() == 1){
        //填空题
            Question question  = new Question();

            question.setTid(questionBean.getTid());
            question.setQtitle(questionBean.getQtitle());
            question.setQanswer(questionBean.getQanswer());
            question.setQstyle(questionBean.getQstyle());

            int result = questionMapper.insert(question);
            if(result > 0){
                map =ReturnHelper.success("success");
            }else {
                map = ReturnHelper.fail("insert fail");
            }

        }else {
            map = ReturnHelper.fail("style error");
        }
        return map;
    }

    @Transactional
    @Override
    public Map<String, Object> showAllQuestion(Integer tid) throws Exception {
        Map<String, Object> map = new HashMap<>();

        List<Question> questionlist = questionMapper.selectByTid(tid);

        if(null != questionlist && questionlist.size()>0){
            List<QuestionBussiness> questionBussinesseslist = new ArrayList<>();

            for(int i=0;i<questionlist.size();i++){
                QuestionBussiness questionBussiness = new QuestionBussiness();

                Question question = questionlist.get(i);

                if(question.getQstyle() == 0){
                    //选择题
                    questionBussiness.setQid(question.getQid());
                    questionBussiness.setQtitle(question.getQtitle());
                    questionBussiness.setQanswer(question.getQanswer());
                    questionBussiness.setQstyle(question.getQstyle());

                    //获取选项
                    Select select = selectMapper.selectByQid(question.getQid());
                    //将json字符串转换为json对象s
                    JSONObject obj = JSON.parseObject(select.getOptions());

                    System.out.println(obj.getString("A"));
                    Options options = new Options();
                    options.setOptionA(obj.getString("A"));
                    options.setOptionB(obj.getString("B"));
                    options.setOptionC(obj.getString("C"));
                    options.setOptionD(obj.getString("D"));

                    questionBussiness.setOptions(options);

                    questionBussinesseslist.add(questionBussiness);
                }else if(question.getQstyle() == 1){
                    //填空题
                    questionBussiness.setQid(question.getQid());
                    questionBussiness.setQtitle(question.getQtitle());
                    questionBussiness.setQanswer(question.getQanswer());
                    questionBussiness.setQstyle(question.getQstyle());

                    questionBussinesseslist.add(questionBussiness);
                }
            }
            map = ReturnHelper.success("success");
            map.put("questionlist",questionBussinesseslist);
        }else {
            map = ReturnHelper.fail("获取失败，无题");
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteQuestion(Integer qid) throws Exception {
        Map<String, Object> map = new HashMap<>();
        int result = questionMapper.deleteByPrimaryKey(qid);
        if(result>0){
            map = ReturnHelper.success("success");
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }

    @Transactional
    @Override
    public Map<String, Object> updateQuestion(QuestionBean questionBean) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if(questionBean.getQstyle() == 0){
            //选择题
            Question question = new Question();
            question.setTid(questionBean.getTid());
            question.setQid(questionBean.getQid());
            question.setQtitle(questionBean.getQtitle());
            question.setQstyle(questionBean.getQstyle());
            question.setQanswer(questionBean.getQanswer());
            //返回自增主键
            int result = questionMapper.updateByPrimaryKeySelective(question);

            if(result > 0){
                Select select = new Select();
                select.setQid(questionBean.getQid());
                select.setOptions(questionBean.getOptions());

                int result2 = selectMapper.updateByQid(select);

                if(result2 > 0){
                    map = ReturnHelper.success("success");
                }else {
                    map = ReturnHelper.fail("select update fail");
                }
            }else {
                map = ReturnHelper.fail("question update fail");
            }

        }else if(questionBean.getQstyle() == 1){
            //填空题
            Question question  = new Question();

            question.setTid(questionBean.getTid());
            question.setQid(questionBean.getQid());
            question.setQtitle(questionBean.getQtitle());
            question.setQstyle(questionBean.getQstyle());
            question.setQanswer(questionBean.getQanswer());

            int result = questionMapper.updateByPrimaryKeySelective(question);
            if(result > 0){
                map =ReturnHelper.success("success");
            }else {
                map = ReturnHelper.fail("update fail");
            }
        }else {
            map = ReturnHelper.fail("style error");
        }
        return map;
    }



}
