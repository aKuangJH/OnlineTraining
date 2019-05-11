package com.cn.train.entity.bussiness;

/**
 * @description:
 * @author: JiaHao.Kuang
 * @create: 2019-04-14 00:14
 **/
public class TestBusiness {
    private Integer tid;

    private String tname;

    private String username;

    private String createtime;

    private String status;

    private Integer answercount;

    private Integer testauthority;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getAnswercount() {
        return answercount;
    }

    public void setAnswercount(Integer answercount) {
        this.answercount = answercount;
    }

    public Integer getTestauthority() {
        return testauthority;
    }

    public void setTestauthority(Integer testauthority) {
        this.testauthority = testauthority;
    }
}
