package com.mdl.email;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class JavaMailUtils {

    // 邮件发送协议 
    private final static String PROTOCOL = "smtp"; 
     
    // SMTP邮件服务器 
    private final static String HOST = "smtp.163.com"; 
     
    // SMTP邮件服务器默认端口 
    private final static String PORT = "25"; 
     
    // 是否要求身份认证 
    private final static String IS_AUTH = "true"; 
     
    // 是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息） 
    private final static String IS_ENABLED_DEBUG_MOD = "true"; 
     
    // 发件人 
    private static String from = "xie_kun_cloud@163.com"; 
 
    // 初始化连接邮件服务器的会话信息 
    private static Properties props = null; 
    
    private static String fromName = "方正璞华云监控";
    
     
    static { 
        props = new Properties(); 
        props.setProperty("mail.transport.protocol", PROTOCOL); 
        props.setProperty("mail.smtp.host", HOST); 
        props.setProperty("mail.smtp.port", PORT); 
        props.setProperty("mail.smtp.auth", IS_AUTH); 
        props.setProperty("mail.debug",IS_ENABLED_DEBUG_MOD); 
    } 
 
     
//    public static void main(String[] args) throws Exception {
//    	
//    	  // 收件人 
//          String to = "869118563@qq.com"; 
//    	
//    	  String subjectBody = "方正璞华云监控";
//    	
//    	
//    	  // 1.发送文本邮件 
//    	  String textEmailBody = "XX用户你好, 璞华云监控欢迎您，我们提供专业的云监控服务，让您更清晰的了解您的服务器情况……";
//    	  sendTextEmail(to,subjectBody,textEmailBody); 
//         
//        
////        // 2.发送简单的html邮件 
////        String emailBody ="<span style='color:red;'>方正璞华云监控</span>"
////        		+"<h4>神一样的监控你</h4> "
////        		+"<a href='javascript:void(0)' onclick='www.purvar.com'>purvar官网</a>";  
////        JavaMailUtils.sendHtmlEmail(to,subjectBody,emailBody); 
//         
//        
////        // 3.发送带内嵌图片的HTML邮件 
////        String htmlBody = "<span style='color:red;font-size:20px'>方正璞华云监控，神一样的监控你！！！</br><img src='cid:fengyu'/></span>";
////        String picPath = "E:\\fengyu.jpeg";
////        String fileName = "fengyu";
////        JavaMailUtils.sendHtmlWithInnerImageEmail(to,subjectBody,htmlBody,picPath,fileName); 
//     
//        
////        // 4.发送混合组合邮件 
////        String filePath = "E:\\test.txt";
////        String picPath = "E:\\fengyu.jpeg";
////        String fileName = "fengyu";
////        String emailBody ="<span style='color:red;'>方正璞华云监控</span>"
////  						 +"<h4>神一样的监控你</h4> "
////  						 +"<img src='cid:fengyu'/>"; 
////        String userName = "尊敬的xx用户";
////        JavaMailUtils.sendMultipleEmail(to,userName,subjectBody,emailBody,picPath,filePath,fileName); 
//        
//    } 
    
    
    /**
     * 发送简单的文本邮件
     */ 
    public static void sendTextEmail(String to,String subjectBody,String emailBody) throws Exception { 
        // 创建Session实例对象 
        Session session = Session.getDefaultInstance(props); 
        // 创建MimeMessage实例对象 
        MimeMessage message = new MimeMessage(session); 
        // 设置发件人 
        message.setFrom(new InternetAddress(from)); 
        // 设置邮件主题 
        message.setSubject(subjectBody); 
        // 设置收件人 
        message.setRecipient(RecipientType.TO, new InternetAddress(to)); 
        // 设置发送时间 
        message.setSentDate(new Date()); 
        // 设置纯文本内容为邮件正文 
        message.setText(emailBody); 
        // 保存并生成最终的邮件内容 
        message.saveChanges(); 
        // 获得Transport实例对象 
        Transport transport = session.getTransport(); 
        // 打开连接 
        transport.connect("xie_kun_cloud", "986753xk"); 
        // 将message对象传递给transport对象，将邮件发送出去 
        transport.sendMessage(message, message.getAllRecipients()); 
        // 关闭连接 
        transport.close(); 
    } 
     
    /**
     * 发送简单的html邮件
     */ 
    public static void sendHtmlEmail(String to,String subjectBody,String emailBody) throws Exception { 
    	
        // 创建Session实例对象 
        Session session = Session.getInstance(props, new MyAuthenticator()); 
        // 创建MimeMessage实例对象 
        MimeMessage message = new MimeMessage(session); 
        // 设置邮件主题   
        message.setSubject(subjectBody); 
        // 设置发送人 
        message.setFrom(new InternetAddress(from));
        // 设置发送时间 
        message.setSentDate(new Date()); 
        // 设置收件人 
        message.setRecipients(RecipientType.TO, InternetAddress.parse(to)); 
        // 设置html内容为邮件正文，指定MIME类型为text/html类型，并指定字符编码为UTF-8或gbk 
        message.setContent(emailBody,"text/html;charset=UTF-8"); 
        // 保存并生成最终的邮件内容 
        message.saveChanges();
        // 发送邮件 
        Transport.send(message);
    } 
     
    /**
     * 发送带内嵌图片的HTML邮件
     * @param emailBody
     * @param subjectBody
     * @param picPath
     * @param fileName
     * @throws MessagingException
     */
    public static void sendHtmlWithInnerImageEmail(String to,String subjectBody,String emailBody,String picPath,String fileName) throws MessagingException { 
    	
        // 创建Session实例对象 
        Session session = Session.getDefaultInstance(props, new MyAuthenticator()); 
        // 创建邮件内容 
        MimeMessage message = new MimeMessage(session); 
        // 邮件主题,并指定编码格式 
        message.setSubject(subjectBody, "utf-8");     
        // 发件人 
        message.setFrom(new InternetAddress(from)); 
        // 收件人 
        message.setRecipients(RecipientType.TO, InternetAddress.parse(to)); 
//        // 抄送 
//        message.setRecipient(RecipientType.CC, new InternetAddress("123@163.com")); 
//        // 密送 (不会在邮件收件人名单中显示出来) 
//        message.setRecipient(RecipientType.BCC, new InternetAddress("456@163.com")); 
        // 发送时间 
        message.setSentDate(new Date()); 
         
        // 创建一个MIME子类型为“related”的MimeMultipart对象 
        MimeMultipart mp = new MimeMultipart("related");
        
        // 创建一个表示正文的MimeBodyPart对象，并将它加入到前面创建的MimeMultipart对象中 
        MimeBodyPart htmlPart = new MimeBodyPart(); 
        mp.addBodyPart(htmlPart); 
        
        // 创建一个表示图片资源的MimeBodyPart对象，将将它加入到前面创建的MimeMultipart对象中 
        MimeBodyPart imagePart = new MimeBodyPart(); 
        mp.addBodyPart(imagePart); 
         
        // 将MimeMultipart对象设置为整个邮件的内容 
        message.setContent(mp); 
         
        // 设置内嵌图片邮件体 
        DataSource ds = new FileDataSource(new File(picPath)); 
        DataHandler dh = new DataHandler(ds); 
        imagePart.setDataHandler(dh); 
        
        //获取图片路径中的图片名字作为contentId，在邮件html内容中引用img时，使用<img src='cid:contentId'/>，便于在邮件内容中显示图片
//        String contentId = picPath.substring(picPath.lastIndexOf("\\")+1,picPath.indexOf("."));
        imagePart.setContentID(fileName);  // 设置内容编号,用于其它邮件体引用 
        
        // 创建一个MIME子类型为"alternative"的MimeMultipart对象，并作为前面创建的htmlPart对象的邮件内容 
        MimeMultipart htmlMultipart = new MimeMultipart("alternative"); 
        
        // 创建一个表示html正文的MimeBodyPart对象 
        MimeBodyPart htmlBodypart = new MimeBodyPart();
        
        // 其中cid=androidlogo.gif是引用邮件内部的图片，即imagePart.setContentID("androidlogo.gif");方法所保存的图片 
        htmlBodypart.setContent(emailBody,"text/html;charset=utf-8"); 
        htmlMultipart.addBodyPart(htmlBodypart); 
        htmlPart.setContent(htmlMultipart); 
         
        // 保存并生成最终的邮件内容 
        message.saveChanges(); 
         
        // 发送邮件 
        Transport.send(message); 
    } 
     
    /**
     * 发送带内嵌图片、附件、多收件人(显示邮箱姓名)、邮件优先级、阅读回执的完整的HTML邮件
     */ 
//  public static void sendMultipleEmail(String subjectBody,String emailBody,String[] picPath,String[] picPath,String[] fileName,String replyAddress) throws Exception {
    
    public static void sendMultipleEmail(String to,String userName,String subjectBody,String emailBody,String picPath,String filePath,String fileName) throws Exception {
    	String charset = "utf-8";   // 指定中文编码格式 
        // 创建Session实例对象 
        Session session = Session.getInstance(props,new MyAuthenticator()); 
         
        // 创建MimeMessage实例对象 
        MimeMessage message = new MimeMessage(session); 
        // 设置主题 
        message.setSubject(subjectBody); 
        // 设置发送人 
        message.setFrom(new InternetAddress(from,fromName,charset)); 
        // 设置收件人 
        message.setRecipients(RecipientType.TO,  
                new Address[] { 
                // 参数1：邮箱地址，参数2：姓名（在客户端收件只显示姓名，而不显示邮件地址），参数3：姓名中文字符串编码 
                new InternetAddress(to, userName, charset), 
//              new InternetAddress("869118563@qq.com", "李四_163", charset), 
            } 
        ); 
//      // 设置抄送 
//        message.setRecipient(RecipientType.CC, new InternetAddress("zhangsan@163.com","张三",charset)); 
        // 设置密送 
//        message.setRecipient(RecipientType.BCC, new InternetAddress("lisi@qq.com", "李四", charset)); 
        // 设置发送时间 
        message.setSentDate(new Date()); 
        // 设置回复人(收件人回复此邮件时,默认收件人) 
        message.setReplyTo(InternetAddress.parse("\"" + MimeUtility.encodeText(fromName) + "\" <xie_kun_cloud@163.com>")); 
        // 设置优先级(1:紧急   3:普通    5:低) 
        message.setHeader("X-Priority", "1"); 
        // 要求阅读回执(收件人阅读邮件时会提示回复发件人,表明邮件已收到,并已阅读) 
        message.setHeader("Disposition-Notification-To", from); 
         
        // 创建一个MIME子类型为"mixed"的MimeMultipart对象，表示这是一封混合组合类型的邮件 
        MimeMultipart mailContent = new MimeMultipart("mixed");  
        message.setContent(mailContent); 
         
        // 附件 
        MimeBodyPart attach2 = new MimeBodyPart(); 
        // 内容 
        MimeBodyPart mailBody = new MimeBodyPart(); 
         
        // 将附件和内容添加到邮件当中 
        mailContent.addBodyPart(attach2); 
        mailContent.addBodyPart(mailBody); 
         
        // 非图片附件
        DataSource ds2 = new FileDataSource(filePath); 
        DataHandler dh2 = new DataHandler(ds2); 
        attach2.setDataHandler(dh2); 
        
        //供显示的附件名字
        String mFileName = filePath.substring(filePath.lastIndexOf("\\")+1,filePath.length());
        attach2.setFileName(MimeUtility.encodeText(mFileName)); 
        
        // 邮件正文(内嵌图片+html文本) 
        MimeMultipart body = new MimeMultipart("related");  //邮件正文也是一个组合体,需要指明组合关系 
        mailBody.setContent(body); 
         
        // 邮件正文由html和图片构成 
        MimeBodyPart imgPart = new MimeBodyPart(); 
        MimeBodyPart htmlPart = new MimeBodyPart(); 
        body.addBodyPart(imgPart); 
        body.addBodyPart(htmlPart); 
         
        // 正文图片 
        DataSource ds3 = new FileDataSource("E:\\fengyu.jpeg"); 
        DataHandler dh3 = new DataHandler(ds3); 
        imgPart.setDataHandler(dh3); 
        imgPart.setContentID("fengyu");
         
        // html邮件内容 
        MimeMultipart htmlMultipart = new MimeMultipart("alternative");  
        htmlPart.setContent(htmlMultipart); 
        MimeBodyPart htmlContent = new MimeBodyPart(); 
        htmlContent.setContent(emailBody, "text/html;charset=utf-8"); 
        htmlMultipart.addBodyPart(htmlContent); 
         
        // 保存邮件内容修改 
        message.saveChanges(); 
         
        // 发送邮件 
        Transport.send(message); 
    } 
     
    /**
     * 向邮件服务器提交认证信息
     */ 
    static class MyAuthenticator extends Authenticator { 
         
        private String username = "xie_kun_cloud"; 
         
        private String password = "986753xk"; 
         
        public MyAuthenticator() { 
            super(); 
        } 
 
        public MyAuthenticator(String username, String password) { 
            super(); 
            this.username = username; 
            this.password = password; 
        } 
 
        @Override 
        protected PasswordAuthentication getPasswordAuthentication() { 
             
            return new PasswordAuthentication(username, password); 
        } 
    } 

	
}
