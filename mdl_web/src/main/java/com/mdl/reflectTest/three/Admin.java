package com.mdl.reflectTest.three;

import java.lang.reflect.Method;

public class Admin {

	public static void main(String[] args) {
		try {
			// 赋值
			Object obj = MyBean.class.newInstance();
			Class paramClass = Class.forName("[Ljava.lang.String;");
			String[] param = { "吃", "喝", "玩", "乐" };
			Method method = MyBean.class.getMethod("setLove", paramClass);
			method.invoke(obj, (Object) param);

			// 取值
			MyBean bean = (MyBean) obj;
			for (int i = 0; i < bean.getLove().length; i++) {
				System.out.println(bean.getLove()[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
