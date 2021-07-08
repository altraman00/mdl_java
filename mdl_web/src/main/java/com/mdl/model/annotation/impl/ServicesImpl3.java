package com.mdl.model.annotation.impl;

import com.mdl.model.annotation.Services;

public class ServicesImpl3 implements Services{

	@Override
	public void print(String str) {
		System.out.println("打印3：" + str);
	}

}
