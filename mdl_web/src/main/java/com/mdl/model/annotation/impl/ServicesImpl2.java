package com.mdl.model.annotation.impl;

import org.springframework.stereotype.Service;

import com.mdl.model.annotation.Services;


@Service("testServicesImpl2")
public class ServicesImpl2 implements Services{

	@Override
	public void print(String str) {
		System.out.println("打印2：" + str);
	}

}
