package com.mdl.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mdl.model.annotation.Services;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContent.xml" })
public class TestAnnotation2 {

	@Autowired
	@Qualifier("testServicesImpl2")
	private Services testServices;

	@Test
	public void printInfo() {

		testServices.print("测试注解……");
	}

}
