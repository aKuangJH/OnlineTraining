package com.cn.train.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.train.dao.QuestionCollectionMapper;
import com.cn.train.dao.QuestionMapper;
import com.cn.train.dao.SelectMapper;
import com.cn.train.entity.Question;
import com.cn.train.entity.QuestionCollection;
import com.cn.train.entity.Select;
import com.cn.train.entity.bussiness.Options;
import com.cn.train.entity.bussiness.QuestionBusiness;
import com.cn.train.service.QuestionCollectionService;
import com.cn.train.utils.ReturnHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: JiaHao.Kuang
 * @create: 2019-04-22 10:44
 **/
@Service
public class QuestionCollectionImpl implements QuestionCollectionService {

    @Autowired
    QuestionCollectionMapper questionCollectionMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    SelectMapper selectMapper;

    @Override
    public Map<String, Object> showAllQuestions(Integer uid) throws Exception {
        Map<String, Object> map;

        List<QuestionCollection> existquestion = questionCollectionMapper.getAllQuestionCollection(uid);

        if(null != existquestion && existquestion.size() > 0){
            List<QuestionBusiness> questionBussinesseslist = new ArrayList<>();

            for(int i=0;i<existquestion.size();i++){
                Question question = questionMapper.selectByPrimaryKey(existquestion.get(i).getQid());
                QuestionBusiness questionBusiness = new QuestionBusiness();

                if(question.getQstyle() == 0){
                    //选择题
                    questionBusiness.setQid(question.getQid());
                    questionBusiness.setQtitle(question.getQtitle());
                    questionBusiness.setQanswer(question.getQanswer());
                    questionBusiness.setQstyle(question.getQstyle());

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

                    questionBusiness.setOptions(options);

                    questionBussinesseslist.add(questionBusiness);
                }else if(question.getQstyle() == 1){
                    //填空题
                    questionBusiness.setQid(question.getQid());
                    questionBusiness.setQtitle(question.getQtitle());
                    questionBusiness.setQanswer(question.getQanswer());
                    questionBusiness.setQstyle(question.getQstyle());

                    questionBussinesseslist.add(questionBusiness);
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
    public Map<String, Object> cancleCollectQuestion(Integer uid, Integer qid) throws Exception {
        Map<String, Object> map;
        int result = questionCollectionMapper.deleteByUidAndQid(uid, qid);
        if(result > 0){
            map = ReturnHelper.success("success");
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }

    @Override
    public Map<String, Object> collectQuestion(Integer uid, Integer qid) throws Exception {
        Map<String, Object> map;
        QuestionCollection questionCollection = new QuestionCollection();
        questionCollection.setQid(qid);
        questionCollection.setUid(uid);
        int result = questionCollectionMapper.insert(questionCollection);
        if(result > 0){
            map = ReturnHelper.success("success");
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }
}
