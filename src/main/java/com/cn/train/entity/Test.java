package com.cn.train.entity;

import java.util.Date;

public class Test {
    private Integer tid;

    private String tname;

    private Integer uid;

    private Date createtime;

    private String status;

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
        this.tname = tname == null ? null : tname.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getTestauthority() {
        return testauthority;
    }

    public void setTestauthority(Integer testauthority) {
        this.testauthority = testauthority;
    }
}