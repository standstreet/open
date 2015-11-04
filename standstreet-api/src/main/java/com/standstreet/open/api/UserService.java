package com.standstreet.open.api;


import com.alibaba.fastjson.JSONObject;

/**
 * Created by xiaolong.zhu@changhong.com on 2015/11/2.
 */
public interface UserService {
    public JSONObject login(JSONObject login) throws Exception;
}
