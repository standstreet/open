package com.standstreet.open.extension;

import com.alibaba.dubbo.rpc.RpcContext;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Created by wei9.li@changhong.com on 2015/10/5.
 */
public class InternalServerExceptionMapper implements ExceptionMapper<RuntimeException> {
    public Response toResponse(RuntimeException e) {
        System.out.println("Exception mapper successfully got an exception: " + e + ":" + e.getMessage());
        System.out.println("Client IP is " + RpcContext.getContext().getRemoteAddressString());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Oops! Internal Server Error!").type("text/plain").build();
    }
}
