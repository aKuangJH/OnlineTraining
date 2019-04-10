package com.cn.train.view.bean;

/**
 * @description:
 * @author: JiaHao.Kuang
 * @create: 2019-04-07 22:53
 **/
public class QuestionBean {
    private Integer qid;

    private Integer tid;

    private String qtitle;

    private Integer qstyle;

    private String qanswer;

    private String options;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getQtitle() {
        return qtitle;
    }

    public void setQtitle(String qtitle) {
        this.qtitle = qtitle;
    }

    public Integer getQstyle() {
        return qstyle;
    }

    public void setQstyle(Integer qstyle) {
        this.qstyle = qstyle;
    }

    public String getQanswer() {
        return qanswer;
    }

    public void setQanswer(String qanswer) {
        this.qanswer = qanswer;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }
}
