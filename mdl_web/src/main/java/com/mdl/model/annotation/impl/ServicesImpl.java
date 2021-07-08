package com.mdl.model.annotation.impl;

import org.springframework.stereotype.Service;

import com.mdl.model.annotation.Services;

@Service("testServicesImpl")
public class ServicesImpl implements Services{

	@Override
	public void print(String str) {
		System.out.println("打印：" + str);
	}

}
