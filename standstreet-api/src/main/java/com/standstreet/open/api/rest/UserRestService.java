package com.standstreet.open.api.rest;

import com.alibaba.fastjson.JSONObject;

import javax.ws.rs.core.Response;

/**
 * Created by xiaolong.zhu@changhong.com on 2015/11/2.
 */
public interface UserRestService {

    Response login(JSONObject login);
}
