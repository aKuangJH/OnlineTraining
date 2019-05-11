package com.cn.train.entity.bussiness;

/**
 * @description:
 * @author: JiaHao.Kuang
 * @create: 2019-04-25 15:24
 **/
public class InformationBusiness {
    private Integer infoid;

    private String infotitle;

    private String style;

    private String createusername;

    private String createtime;

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
        this.infotitle = infotitle;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getCreateusername() {
        return createusername;
    }

    public void setCreateusername(String createusername) {
        this.createusername = createusername;
    }

    public String getInfocontent() {
        return infocontent;
    }

    public void setInfocontent(String infocontent) {
        this.infocontent = infocontent;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
