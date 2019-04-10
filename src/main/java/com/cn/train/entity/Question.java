package com.cn.train.entity;

public class Question {
    private Integer qid;

    private Integer tid;

    private String qtitle;

    private Integer qstyle;

    private String qanswer;

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

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
        this.qtitle = qtitle == null ? null : qtitle.trim();
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
        this.qanswer = qanswer == null ? null : qanswer.trim();
    }
}