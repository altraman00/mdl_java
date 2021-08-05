package com.mdl.influxdb6.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb6.annotations
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年08月03日 18:10
 * ----------------- ----------------- -----------------
 */

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface InfluxDbHqlMapper {

  String name() default "";

  String database() default "";

  String measurement() default "";

}
