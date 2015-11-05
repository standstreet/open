package com.standstreet.open.exception;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.stereotype.Component;

/**
 * 异常信息处理类
 *
 * Created By xiaolong.zhu@changhong.com on 2015-11-05
 */
@Component
public class TagsNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        registerBeanDefinitionParser("error", new ErrorParser());
    }
}
