package ua.nure.yushin.SummaryTask4.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import ua.nure.yushin.SummaryTask4.entity.User;

public class MailSender {
	
	private static final Logger LOG = Logger.getLogger(MailSender.class);	
	private static final Session SESSION  = init();
	private static final String CONFIRM_URL = "http://localhost:8080/SummaryTask4/Controller?command=showConfirmView&userEmail=";
	private static final String EMAIL_FROM = "summarytask4carrental@gmail.com";
	
	private static Session init() {
		Session session = null;
		
		try {
			InitialContext initialContext = new InitialContext();
			session = (Session) initialContext.lookup("java:comp/env/mail/Session");
		} catch (Exception e) {
			LOG.error(e);			
		}
		return session;		
	}
	
	public static void sendConfirmationMessage (User user) {
		try {
			Message message = new MimeMessage (SESSION);
			InternetAddress internetAdressFrom = new InternetAddress(EMAIL_FROM);
			InternetAddress internetAdressTo = new InternetAddress(user.getUserEmail());
			
			message.setFrom(internetAdressFrom);
			message.addRecipient(Message.RecipientType.TO, internetAdressTo);
			message.setSentDate(new Date());
			
			setContentToConfirmationMessage(message, user);
	
			message.setSentDate(new Date());	
			Transport.send(message);
			LOG.info("Message sends");
		} catch (AddressException e) {
			LOG.error(e);
		} catch (MessagingException e) {
			LOG.error(e);
		} catch (UnsupportedEncodingException e) {
			LOG.error(e);
		}		 
	}
	
	private static void setContentToConfirmationMessage(Message message, User user ) 
			throws MessagingException, UnsupportedEncodingException {
		
		//String md5HexCipher = DigestUtils.md5Hex(user.getUserEmail());
		String confirmLinkName = null;
		String greetings = null;
		String confirmLink = null;
		
		switch (user.getUserLanguage()) {
			case "ru":
				confirmLinkName = "Ссылка для подтверждения регистрации";
				greetings = "Здравствуйте, " +  user.getUserPassSurname()+ " "
						+ user.getUserPassName() + " " + user.getUserPassPatronomic() + " !\n"
						+ "Вы успешно прошли регистрацию на нашем сайте компании по прокату авто \"Юпитер\".\n";
				confirmLink = "Подтвердите регистрацию кликнув по этой "
						+ "<a href= \""+ CONFIRM_URL + user.getUserEmail() + "\">ссылке</a>";
				break;
			case "en":
				confirmLinkName = "A link to confirm your registration";
				greetings = "Hello, " +  user.getUserPassSurname()+ " "
						+ user.getUserPassName() + " " + user.getUserPassPatronomic() + " !\n"
						+ "You have successfully been registered on our company website for rental cars \"Jupiter\".\n\n\n";
				confirmLink = "Confirm your registration by clicking on this "
						+ "<a href= \""+ CONFIRM_URL + user.getUserEmail() + "\">link</a>";
			default:
				break;		
		}
		
		message.setSubject(confirmLinkName);		
		
		Multipart multipart = new MimeMultipart();
		InternetHeaders greetingsHeader = new InternetHeaders();
		InternetHeaders confirmLinkHeader = new InternetHeaders();
		MimeBodyPart greetingAndData = null;
		MimeBodyPart linkAndData = null;
		
		greetingsHeader.addHeader("Content-type", "text/plain; charset=UTF-8");
		LOG.info(greetings);
		LOG.info(greetingsHeader);
		greetingAndData = new MimeBodyPart(greetingsHeader, (greetings).getBytes("UTF-8"));
		confirmLinkHeader.addHeader("Content-type", "text/html; charset=UTF-8");		
		linkAndData = new MimeBodyPart(confirmLinkHeader, confirmLink.getBytes(StandardCharsets.UTF_8));		

		multipart.addBodyPart(greetingAndData);
		multipart.addBodyPart(linkAndData);
		
		message.setContent(multipart);
	}
}
