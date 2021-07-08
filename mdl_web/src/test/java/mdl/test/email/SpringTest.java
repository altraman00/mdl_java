package mdl.test.email;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mdl.model.HelloWorld;


public class SpringTest {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("springmvc-servlet.xml");

		HelloWorld obj = (HelloWorld) context.getBean("helloBean123");
		
		obj.printHello();
	}
}
