package com.mdl.model;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.mdl.model.annotation.Services;

@Component
public class TestAnnotation {

	@Autowired
	@Qualifier("testServicesImpl")
	private Services testServices;

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext cat = new ClassPathXmlApplicationContext("classpath*:applicationContent.xml");
		TestAnnotation bean = cat.getBean(TestAnnotation.class);
		bean.printInfo();
		
	}
	
	@Test
	public void printInfo() {

		testServices.print("测试注解……");
	}

}
