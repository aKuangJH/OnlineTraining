package com.cn.train.entity.bussiness;

/**
 * @description:
 * @author: JiaHao.Kuang
 * @create: 2019-04-26 16:30
 **/
public class HistoryBusiness {

    private Integer ihid;

    private Integer infoid;

    private String infotitle;

    private String style;

    private String createusername;

    private String createtime;

    private String infocontent;

    public Integer getIhid() {
        return ihid;
    }

    public void setIhid(Integer ihid) {
        this.ihid = ihid;
    }

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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getInfocontent() {
        return infocontent;
    }

    public void setInfocontent(String infocontent) {
        this.infocontent = infocontent;
    }
}
