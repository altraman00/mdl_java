package com.mdl.services.impl;

import java.io.File;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.mdl.model.SimpleEmail;
import com.mdl.services.EmailSendManager;

@Component("simpleEmailSendManagerImpl")
public class EmailSendManagerImpl implements EmailSendManager {
	
	@Value("${sys.mail.sysEmailAddress}")
	private String from;  //发送者
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${sys.apmsys.url}")
	private String apmsys_url; //系统地址
	
	@Value("${sys.apmsys.url}")
	private static String apmsys_url_test; //系统地址
	
	@Override
	public void sendEmail(SimpleEmail simpleEmail) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, simpleEmail.isAttachment());
		
		
		System.out.println(apmsys_url);
		
		System.out.println(apmsys_url_test);
		
		/**
		 * 添加发送者
		 */
		helper.setFrom(from);
		
		Set<String> toSet =simpleEmail.getToSet();
		/**
		 * 添加接收者
		 */
		helper.setTo(toSet.toArray(new String[toSet.size()]));
		
		/**
		 * 添加主题
		 */
		helper.setSubject(simpleEmail.getSubject());
		/**
		 * 添加正文
		 */
		helper.setText(simpleEmail.getContent(),simpleEmail.isHtml());
		
		/**
		 * 添加附件
		 */
		if(simpleEmail.isAttachment()){
			Map<String, File> attachments = simpleEmail.getAttachments();
			
			if(attachments != null){
				for(Map.Entry<String, File> attach : attachments.entrySet()){
					helper.addAttachment(attach.getKey(), attach.getValue());
				}
				
			}

		}
		
		mailSender.send(message);  //发送
	}

}

