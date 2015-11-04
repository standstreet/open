package com.standstreet.open.dto;

import java.io.Serializable;

/**
 * Created by xiaolong.zhu@changhong.com on 2015/11/2.
 */
public class UserDto implements Serializable{
    private String id;
    private String loginName;
    private String salt;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
