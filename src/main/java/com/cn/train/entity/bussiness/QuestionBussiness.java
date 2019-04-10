package com.cn.train.entity.bussiness;

/**
 * @description:
 * @author: JiaHao.Kuang
 * @create: 2019-04-08 13:55
 **/
public class QuestionBussiness {
    private Integer qid;

    private String qtitle;

    private String qanswer;

    private Integer qstyle;

    private Options options;



    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public String getQtitle() {
        return qtitle;
    }

    public void setQtitle(String qtitle) {
        this.qtitle = qtitle;
    }

    public String getQanswer() {
        return qanswer;
    }

    public void setQanswer(String qanswer) {
        this.qanswer = qanswer;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public Integer getQstyle() {
        return qstyle;
    }

    public void setQstyle(Integer qstyle) {
        this.qstyle = qstyle;
    }
}
