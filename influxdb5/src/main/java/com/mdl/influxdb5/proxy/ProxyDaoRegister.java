//package com.mdl.influxdb5.proxy;
//
//import com.mdl.influxdb5.dao.UserTestDao;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.BeanDefinitionBuilder;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
//import org.springframework.beans.factory.support.GenericBeanDefinition;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//
///**
// * @Project : demo
// * @Package Name : com.mdl.influxdb5.proxy
// * @Author : xiekun
// * @Desc :
// * @Create Date : 2021年08月03日 16:54
// * ----------------- ----------------- -----------------
// */
//
//@Component
//public class ProxyDaoRegister implements ApplicationContextAware, BeanDefinitionRegistryPostProcessor {
//
//  @Override
//  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
//    // 需要被代理的接口
//    Class<?> cls = UserTestDao.class;
//    BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(cls);
//    GenericBeanDefinition definition = (GenericBeanDefinition) builder.getRawBeanDefinition();
//    //这里是相当于调用BaseDaoProxyFactory中的setInterfaceClass()方法，详细的可以了解spring相关资料
//    definition.getPropertyValues().add("interfaceClass", definition.getBeanClassName());
//    // FactoryBean是一种特殊的Bean，这里的BeanClass是生成Bean实例的工厂，不是Bean本身。其返回的是该工厂Bean的getObject方法所返回的对象。
//    definition.setBeanClass(BaseDaoProxyFactory.class);
//    definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
//    // 注册dao
//    registry.registerBeanDefinition("userTestDao", definition);
//  }
//
//  @Override
//  public void postProcessBeanFactory(
//      ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
//
//  }
//
//  @Override
//  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//
//  }
//}
