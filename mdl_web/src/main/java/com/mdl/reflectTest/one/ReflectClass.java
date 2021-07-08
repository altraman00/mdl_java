package com.mdl.reflectTest.one;

import java.lang.reflect.Field;

public class ReflectClass {

	public static void main(String[] args) {
		Person p = new Person();
		p.setId(0);
		p.setName("张三");
		p.setEmail("123456789@163.com");
		reflect(p);
	}

	public static void reflect(Object obj) {
		if (obj == null)
			return;
		
		Class cls = obj.getClass();
		
//		Field[] fields = obj.getClass().getDeclaredFields();
		
		Field[] fields = cls.getDeclaredFields();
		for (int j = 0; j < fields.length; j++) {
			fields[j].setAccessible(true);
			// 字段名
			System.out.print(fields[j].getName() + ",");
			// 字段值
			if (fields[j].getType().getName().equals(java.lang.String.class.getName())) {
				// String type
				try {
					System.out.print(fields[j].get(obj));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			} else if (fields[j].getType().getName().equals(java.lang.Integer.class.getName())
					|| fields[j].getType().getName().equals("int")) {
				// Integer type
				try {
					System.out.println(fields[j].getInt(obj));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			// 其他类型。。。
		}
		System.out.println();
	}
}
