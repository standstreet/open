package com.standstreet.open.dto;

import java.io.Serializable;

/**
 * Created by xiaolong.zhu@changhong.com on 2015/11/2.
 */
public class UserDto implements Serializable{
    private Long id;                        //用户唯一id
    private String uuid;                    //用户唯一标识uuid
    private String loginName;               //用户登陆账号
    private String password;                //明文密码，不需要持久化
    private String salt;                    //用户密码延值
    private Boolean online;                 //在线状态
    private String phone;                   //手机号码
    private String email;                   //邮箱账号
    private String qq;                      //QQ号码

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
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
