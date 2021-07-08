package com.mdl.email.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.mdl.email.JavaMailUtils;
import com.mdl.freemarker.FreeMarkerUtils;

public class JavaMailTest {

	public static void main(String[] args) throws Exception {
    	
  	  // 收件人 
      String to = "869118563@qq.com"; 
  	
  	  String subjectBody = "方正璞华云监控";
  	
  	
//  	  // 1.发送文本邮件 
//  	  String textEmailBody = "XX用户你好, 璞华云监控欢迎您，我们提供专业的云监控服务，让您更清晰的了解您的服务器情况……";
//  	  JavaMailUtils.sendTextEmail(to,subjectBody,textEmailBody); 
       
      
      // 2.发送简单的html邮件 
//      String emailBody ="<span style='color:red;'>方正璞华云监控</span>"
//      		+"<h4>神一样的监控你</h4> "
//      		+"<a href='javascript:void(0)' onclick='www.purvar.com'>purvar官网</a>"; 
      
      FreeMarkerUtils utl = new FreeMarkerUtils();
      
      Map<String, Object> map = new HashMap<String, Object>();
		map.put("alarmName", "测试报警邮件");
		map.put("alarmTime", "2017-8-25 17:37:22");
		map.put("alarmHost", "host:paas-177");
		map.put("imgUrl", "http://172.29.231.177:8080/apmsys/email_img");
      
      try {
		  String emailBody =  utl.getHtmlContent("/files/ftl", "FMemail.ftl", map);
		  System.out.println("返回的json" + "\n" + emailBody + "\n");
		  JavaMailUtils.sendHtmlEmail(to,subjectBody,emailBody);  
      } catch (IOException e) {
    	  e.printStackTrace();
      }
      
       
      
//      // 3.发送带内嵌图片的HTML邮件 
//      String htmlBody = "<span style='color:red;font-size:20px'>方正璞华云监控，神一样的监控你！！！</br><img src='cid:fengyu'/></span>";
//      String picPath = "E:\\fengyu.jpeg";
//      String fileName = "fengyu";
//      JavaMailUtils.sendHtmlWithInnerImageEmail(to,subjectBody,htmlBody,picPath,fileName); 
   
      
//      // 4.发送混合组合邮件 
//      String filePath = "E:\\test.txt";
//      String picPath = "E:\\fengyu.jpeg";
//      String fileName = "fengyu";
//      String emailBody ="<span style='color:red;'>方正璞华云监控</span>"
//						 +"<h4>神一样的监控你</h4> "
//						 +"<img src='cid:fengyu'/>"; 
//      String userName = "尊敬的xx用户";
//      JavaMailUtils.sendMultipleEmail(to,userName,subjectBody,emailBody,picPath,filePath,fileName); 
      
  } 
	
}
