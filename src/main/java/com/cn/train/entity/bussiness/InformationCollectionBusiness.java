package com.cn.train.entity.bussiness;

/**
 * @description:
 * @author: JiaHao.Kuang
 * @create: 2019-05-12 01:52
 **/
public class InformationCollectionBusiness {
    private Integer icid;

    private Integer infoid;

    private String infotitle;

    private String collecttime;

    public Integer getIcid() {
        return icid;
    }

    public void setIcid(Integer icid) {
        this.icid = icid;
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

    public String getCollecttime() {
        return collecttime;
    }

    public void setCollecttime(String collecttime) {
        this.collecttime = collecttime;
    }
}
