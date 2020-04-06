package by.bobruisk.homework.service;

import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;




@Component
public class EmailServiceImpl {

	@Autowired
	private JavaMailSender mailSender;
	private  final String DESTINATION_EMAIL="dyushka80@gmail.com";

	public void sendSimpleMessage(String title, String message) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(DESTINATION_EMAIL);
		mail.setSubject(title);
		mail.setText(message);						
		mailSender.send(mail);
	}

	public void sendMessageWithAttachment(String title, String message, List<Path> filePaths)
			throws MessagingException, UnsupportedEncodingException, MailSendException{	
		System.setProperty("mail.mime.splitlongparameters", "false");
		MimeMessage mail = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");	
		helper.setTo(DESTINATION_EMAIL);
		helper.setSubject(title);
		helper.setFrom("andrei.vasilyev80@gmail.com");
		helper.setText(message);			
		for (int i=0;i<filePaths.size();i++) {				
			FileSystemResource file = new FileSystemResource(filePaths.get(i));
			helper.addAttachment(MimeUtility.encodeWord(file.getFilename(),null,"B"), file);		
		}		
		mailSender.send(mail);
	}
}
