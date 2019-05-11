package com.cn.train.entity.bussiness;

import com.cn.train.entity.User;

/**
 * @description:
 * @author: JiaHao.Kuang
 * @create: 2019-05-08 17:12
 **/
public class PostCommentBusiness {
    private Integer pcid;

    private User user;

    private String content;

    private String createtime;

    public Integer getPcid() {
        return pcid;
    }

    public void setPcid(Integer pcid) {
        this.pcid = pcid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
