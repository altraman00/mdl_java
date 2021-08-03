package com.mdl.influxdb5.annotations;

import com.mdl.influxdb5.proxy.AutoDaoScanBeanDefinitionRegistrar;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb5.proxy
 * @Author : xiekun
 * @Desc : 扫描dao，并自动创建实现类
 * @Create Date : 2021年08月03日 16:58
 * ----------------- ----------------- -----------------
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AutoDaoScanBeanDefinitionRegistrar.class)
public @interface MyDaoScan {

  @AliasFor("value")
  String[] basePackage() default {};

  @AliasFor("basePackage")
  String[] value() default {};

  String name() default "";

}
