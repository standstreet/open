package com.standstreet.open.extension;

import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.protocol.rest.RpcExceptionMapper;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import org.springside.modules.beanvalidator.BeanValidators;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by wei9.li@changhong.com on 2015/1/8.
 */
public class ValidationExceptionMapper extends RpcExceptionMapper {
    public Response toResponse(RpcException e) {
        // TODO do more sophisticated exception handling and output
        if (e.getCause() instanceof ConstraintViolationException) {
            return handleConstraintViolationException((ConstraintViolationException) e.getCause());
        }
        // we may want to avoid exposing the dubbo exception details to certain clients
        // TODO for now just do plain text output
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal server error: " + e.getMessage()).type(ContentType.TEXT_PLAIN_UTF_8).build();
    }
    protected Response handleConstraintViolationException(ConstraintViolationException cve) {

        Map<String, String> msg = BeanValidators.extractPropertyAndMessage(cve.getConstraintViolations());
//        com.changhong.commons.exception.Error error = ECodeUtil.getCommError(ErrorConstant.PARAM_VALIDATION_ERROR);
//        error.setDes(msg.toString());
        return Response.status(Response.Status.BAD_REQUEST).entity(msg).type("application/json").build();

        // 采用json输出代替xml输出
    }
}
