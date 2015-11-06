package com.standstreet.open.api.impl.rest;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.standstreet.open.api.UserService;
import com.standstreet.open.api.impl.OkModel;
import com.standstreet.open.api.rest.UserRestService;
import com.standstreet.open.dto.UserDto;
import com.standstreet.open.exception.CoreException;
import com.standstreet.open.exception.ECodeUtil;
import com.standstreet.open.exception.ErrorConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by xiaolong.zhu@changhong.com on 2015/11/2.
 */
@SuppressWarnings("ALL")
@Service(protocol = "rest")
@Path("/open/v1/user/")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED, MediaType.TEXT_HTML})
@Produces(MediaType.APPLICATION_JSON)
public class UserRestServiceImpl implements UserRestService {
    private static final Logger logger = LoggerFactory.getLogger(UserRestServiceImpl.class);

    @Autowired
    private UserService userService;


    @Override
    @POST
    @Path("login")
    public Response login(JSONObject login, @Context HttpServletRequest request) {
        try {
            if (login.containsKey("loginName") && login.containsKey("password")) {
                String loginName=login.getString("loginName");
                String password=login.getString("password");
                String ip = request.getHeader("X-Forwarded-For");
                if (ip == null)
                    ip = request.getRemoteHost();
                logger.info("IP:[{}]用户登陆,账号：[{}],密码：[{}]", ip,loginName,password);
                userService.login(ip,loginName,password);
                return Response.status(Response.Status.OK).entity(new OkModel()).build();
            } else {
                logger.info("参数有误");
                throw new CoreException(ECodeUtil.getCommError(ErrorConstant.COMMON_PARAM_IS_ERROR));
            }
        } catch (CoreException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getError()).build();
        }
    }

    @Override
    @POST
    @Path("/register")
    public Response register(UserDto userDto, @Context HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null)
            ip = request.getRemoteHost();
        logger.info("IP:[{}]用户注册:[{}]", ip, JSONObject.toJSONString(userDto));
        try {
            userService.register(ip, userDto);
            return Response.status(Response.Status.CREATED).entity(new OkModel(201, "注册成功")).build();
        } catch (CoreException e) {
            e.printStackTrace();
            logger.info("异常信息：[{}]", JSONObject.toJSONString(e));
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getError()).build();
        }
    }
}
