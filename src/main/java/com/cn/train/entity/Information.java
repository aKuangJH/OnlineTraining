package com.cn.train.entity;

import java.util.Date;

public class Information {
    private Integer infoid;

    private String infotitle;

    private Integer istyid;

    private Integer infoauthority;

    private Integer uid;

    private Date createtime;

    private String infocontent;

    public Integer getInfoid() {
        return infoid;
    }

    public void setInfoid(Integer infoid) {
        this.infoid = infoid;
    }

    public String getInfotitle() {
        return infotitle;
    }

    public void setInfotitle(String infotitle) {
        this.infotitle = infotitle == null ? null : infotitle.trim();
    }

    public Integer getIstyid() {
        return istyid;
    }

    public void setIstyid(Integer istyid) {
        this.istyid = istyid;
    }

    public Integer getInfoauthority() {
        return infoauthority;
    }

    public void setInfoauthority(Integer infoauthority) {
        this.infoauthority = infoauthority;
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

    public String getInfocontent() {
        return infocontent;
    }

    public void setInfocontent(String infocontent) {
        this.infocontent = infocontent == null ? null : infocontent.trim();
    }
}