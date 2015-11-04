package com.standstreet.open.dao.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识MyBatis的DAO,方便MapperScannerConfigurer的扫描。
 * 
 * @author wei9.li@changhong.com
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyBatisRepository {
	
}
