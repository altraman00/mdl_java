package com.mdl.influxdb6.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb4.annotations
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年07月30日 17:10
 * ----------------- ----------------- -----------------
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Documented
public @interface InfluxDbHql {

    String value() default "";

//    String database() default "";
//
//    String measurement() default "";

    boolean nativeQuery() default false;

}
