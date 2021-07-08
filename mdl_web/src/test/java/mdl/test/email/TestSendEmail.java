package mdl.test.email;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mdl.model.SimpleEmail;
import com.mdl.services.EmailSendManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:springmvc-servlet.xml" })
public class TestSendEmail {

	@Resource(name = "simpleEmailSendManagerImpl")
	private EmailSendManager emailSendManager;
	
	@Value("${sys.apmsys.url}")
	private String apmsys_url; //系统地址

	/**
	 * 发送HTML格式的邮件
	 * 
	 * @throws MessagingException
	 */
	@Test
	public void sendHTMLEmail() throws MessagingException {
		SimpleEmail simpleEmail = new SimpleEmail();
		simpleEmail.setSubject("测试在Spring中发送带HTML格式的邮件");

		System.out.println(apmsys_url);
		
		Set<String> receivers = new HashSet<>();
		receivers.add("869118563@qq.com");
		simpleEmail.setToSet(receivers);

		simpleEmail.setHtml(true);

		simpleEmail.setContent("<html><head><meta http-equiv=\"Content-Type\" "
				+ "content=\"text/html; charset=UTF-8\"></head>" + "<body><div align=\"center\" style=\"color:#F00\">"
				+ "<h2>测试在Spring中发送带HTML格式的邮件</h2></div></body></html>");

		simpleEmail.setAttachment(false);

		emailSendManager.sendEmail(simpleEmail);
	}
}
