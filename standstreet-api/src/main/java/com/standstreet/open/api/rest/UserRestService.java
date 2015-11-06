package com.standstreet.open.api.rest;

import com.alibaba.fastjson.JSONObject;
import com.standstreet.open.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

/**
 * Created by xiaolong.zhu@changhong.com on 2015/11/2.
 */
public interface UserRestService {

    Response login(JSONObject login,HttpServletRequest request);

    Response register(UserDto userDto,HttpServletRequest request);
}
