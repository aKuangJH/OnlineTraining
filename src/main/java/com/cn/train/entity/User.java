package com.cn.train.entity;

public class User {
    private Integer uid;

    private String username;

    private String realname;

    private String password;

    private String email;

    private String description;

    private String phone;

    private Integer role;

    private String salt;

    private String uimg;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getUimg() {
        return uimg;
    }

    public void setUimg(String uimg) {
        this.uimg = uimg == null ? null : uimg.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                ", salt='" + salt + '\'' +
                ", uimg='" + uimg + '\'' +
                '}';
    }
}