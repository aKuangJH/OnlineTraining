package com.cn.train.entity;

import java.util.Date;

public class PostComment {
    private Integer pcid;

    private Integer uid;

    private Integer infoid;

    private String content;

    private Date createtime;

    public Integer getPcid() {
        return pcid;
    }

    public void setPcid(Integer pcid) {
        this.pcid = pcid;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}