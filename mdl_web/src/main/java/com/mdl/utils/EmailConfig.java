//package com.mdl.utils;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.MailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//@Configuration
//public class EmailConfig {
//
//	  @Value("${sys.mail.host}")
//	  private String host;
//	  
//	  @Value("${sys.mail.smtp.port}")
//	  private String port;
//	  
//	  @Value("${sys.mail.sysEmailAddress}")
//	  private String username;
//	  
//	  @Value("${sys.mail.password}")
//	  private String password;
//	
//	@Bean
//	public MailSender mailSender(){
//		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
//		mailSenderImpl.setDefaultEncoding("UTF-8");
//		mailSenderImpl.setHost(host);
//		mailSenderImpl.setPort(Integer.valueOf(port));
//		mailSenderImpl.setUsername(username);
//		mailSenderImpl.setPassword(password);
//		
//		return mailSenderImpl;
//	}
//
//}
//
