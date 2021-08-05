package com.mdl.influxdb6.proxy;

import com.mdl.influxdb6.annotations.InfluxDbHqlMapper;
import java.util.Set;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * @Project : demo
 * @Package Name : com.mdl.influxdb6.proxy
 * @Author : xiekun
 * @Desc :
 * @Create Date : 2021年08月03日 17:01
 * ----------------- ----------------- -----------------
 */
public class DaoScanClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

  public DaoScanClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
    super(registry);
  }

  @Override
  protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
    return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata()
        .isIndependent();
  }

  @Override
  protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
    //添加过滤条件，这里是只添加了@MyDaoScan的注解才会被扫描到
    addIncludeFilter(new AnnotationTypeFilter(InfluxDbHqlMapper.class));

    Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
    processBeanDefinitions(beanDefinitionHolders);
    //返回的是beandefinition
    return beanDefinitionHolders;
  }

  private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitionHolders) {
    beanDefinitionHolders.forEach(e -> {
      GenericBeanDefinition definition = (GenericBeanDefinition) e.getBeanDefinition();
      //这里是相当于调用BaseDaoProxyFactory中的setInterfaceClass()方法，详细的可以了解spring相关资料
      definition.getPropertyValues().add("interfaceClass", definition.getBeanClassName());
      // FactoryBean是一种特殊的Bean，这里的BeanClass是生成Bean实例的工厂，不是Bean本身。其返回的是该工厂Bean的getObject方法所返回的对象。
      definition.setBeanClass(BaseDaoProxyFactory.class);
      definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
    });
  }


}
