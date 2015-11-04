package com.standstreet.open.api.impl.rest;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.standstreet.open.api.UserService;
import com.standstreet.open.api.rest.UserRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
public class UserRestServiceImpl implements UserRestService{
    private static final Logger logger= LoggerFactory.getLogger(UserRestServiceImpl.class);

    @Autowired
    private UserService userService;


    @Override
    @POST
    @Path("login")
    public Response login(JSONObject login) {
        try {
            JSONObject result=userService.login(login);
            return Response.status(Response.Status.OK).entity(result).build();
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject error=new JSONObject();
            error.put("error",e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }
    }
}
