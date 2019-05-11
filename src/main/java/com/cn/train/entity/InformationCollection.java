package com.cn.train.entity;

import java.util.Date;

public class InformationCollection {
    private Integer icid;

    private Integer uid;

    private Integer infoid;

    private Date collecttime;

    public Integer getIcid() {
        return icid;
    }

    public void setIcid(Integer icid) {
        this.icid = icid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getInfoid() {
        return infoid;
    }

    public void setInfoid(Integer infoid) {
        this.infoid = infoid;
    }

    public Date getCollecttime() {
        return collecttime;
    }

    public void setCollecttime(Date collecttime) {
        this.collecttime = collecttime;
    }
}