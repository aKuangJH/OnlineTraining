package com.cn.train.entity;

public class Style {
    private Integer istyid;

    private String style;

    public Integer getIstyid() {
        return istyid;
    }

    public void setIstyid(Integer istyid) {
        this.istyid = istyid;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style == null ? null : style.trim();
    }
}