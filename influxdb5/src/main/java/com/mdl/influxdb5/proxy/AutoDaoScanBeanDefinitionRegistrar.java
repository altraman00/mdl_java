package com.mdl.influxdb5.proxy;

import com.mdl.influxdb5.annotations.MyDaoScan;
import java.util.Set;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb5.proxy
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年08月03日 17:00
 * ----------------- ----------------- -----------------
 */
public class AutoDaoScanBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar,
    ResourceLoaderAware, BeanFactoryAware {

  private ResourceLoader resourceLoader;

  private BeanFactory beanFactory;

  @Override
  public void setResourceLoader(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;

  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    this.beanFactory = beanFactory;
  }

  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
      BeanDefinitionRegistry registry) {
    // 获取MapperScan注解属性信息
    AnnotationAttributes annotationAttributes = AnnotationAttributes
        .fromMap(importingClassMetadata.getAnnotationAttributes(MyDaoScan.class.getName()));
    // 获取注解的属性值,拿到定义的扫描路径
    String[] basePackages = annotationAttributes.getStringArray("basePackage");
    // 使用自定义扫描器扫描
    DaoScanClassPathBeanDefinitionScanner scanHandle = new DaoScanClassPathBeanDefinitionScanner(
        registry);

    if(resourceLoader != null){
      scanHandle.setResourceLoader(resourceLoader);
    }
    //这里实现的是根据名称来注入
    scanHandle.setBeanNameGenerator(new CustomerBeanNameGenerator());
    //扫描指定路径下的接口
    scanHandle.doScan(basePackages);
  }


}
