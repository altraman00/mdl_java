package com.mdl.ioc;

import com.mdl.ioc.factory.BeanFactory;
import com.mdl.ioc.factory.ClassPathXmlApplicationContext;

public class IocTest {

	public static void main(String[] args) throws Exception {
		//加载配置文件  
        BeanFactory f = new ClassPathXmlApplicationContext("IocApplication.xml");  

        //英格兰  
        Object oe = f.getBean("E");  
        Team e = (Team)oe;  
        e.say();  

        //西班牙  
        Object os = f.getBean("S");  
        Team s = (Team)os;  
        s.say();  

        //葡萄牙  
        Object op = f.getBean("P");  
        Team p = (Team)op;  
        p.say();  
	}
	
}
