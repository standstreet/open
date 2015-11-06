package com.standstreet.open.api.impl;

/**
 * Created by xiaolong.zhu@changhong.com on 2015/11/6.
 */
public class OkModel {
    private int code = 200;
    private String msg = "操作成功";
    private String des = "操作成功";

    public OkModel() {
    }

    public OkModel(int code, String msg, String des) {
        this.code = code;
        this.msg = msg;
        this.des = des;
    }

    public OkModel(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
