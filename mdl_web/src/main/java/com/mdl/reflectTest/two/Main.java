package com.mdl.reflectTest.two;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Student student = new Student();
		
		one(student);
		
		two();
		
		three();
		
		four();
		
	}
	
	private static void one(Object o) throws Exception{
		
		Class cls = o.getClass();
		
		Object obj = cls.newInstance();
		
		Method m = cls.getMethod("hi", new Class[]{int.class,String.class});
		
		m.invoke(obj, 20,"aaaa");
	}
	
	
	
	private static void two() throws Exception{

		Class cls = Class.forName("com.mdl.reflectTest.two.Student");
		
		Object obj = cls.newInstance();
		
		Method m = cls.getMethod("hi", new Class[]{int.class,String.class});
		
		m.invoke(obj, 20,"aaaa");
	
	}
	
	private static void three() throws Exception{
		
		Class cls = Class.forName("com.mdl.reflectTest.two.Student");
		
		Object st = cls.newInstance();
		
		Field field = cls.getDeclaredField("age");
		
		field.setAccessible(true);//设置允许访问
		
		field.set(st, 10);
		
		System.out.println(field.get(st));
		
	}
	
	private static void four() throws Exception{
		
		Class cls = Class.forName("com.mdl.reflectTest.two.Student");
		
		Object st = cls.newInstance();
		
		Method setMethod = cls.getDeclaredMethod("setAge", int.class);
		
		setMethod.invoke(st, 15);//调用set方法
		
		Method getMethod = cls.getDeclaredMethod("getAge");
		
		System.out.println(getMethod.invoke(st));//再调用get方法
		
	}
	
}

















