package com.mdl.ioc.factory;

//工厂接口  
public interface BeanFactory {
	
	Object getBean(String id);  
	
}
