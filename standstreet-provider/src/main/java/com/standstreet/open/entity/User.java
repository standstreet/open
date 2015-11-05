package com.standstreet.open.entity;

/**
 * Created by xiaolong.zhu@changhong.com on 2015/11/2.
 */
public class User {
    private Long id;
    private String uuid;
    private String loginName;
    private String password;                //不需要持久化
    private String salt;
    private String encodePassword;
    private Boolean online;
    private String phone;
    private String email;
    private String qq;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEncodePassword() {
        return encodePassword;
    }

    public void setEncodePassword(String encodePassword) {
        this.encodePassword = encodePassword;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}
