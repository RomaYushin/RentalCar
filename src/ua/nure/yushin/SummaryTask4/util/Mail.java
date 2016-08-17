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
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import com.sun.mail.smtp.DigestMD5;

import ua.nure.yushin.SummaryTask4.entity.User;

public class Mail {
	
	private static final Logger LOG = Logger.getLogger(Mail.class);	
	private static final Session SESSION = init();
	private static final String CONFIRM_RU = "Ссылка для подтверждения регистрации";
	private static final String CONFIRM_EN = "A link to confirm your registration";
	private static final String CONFIRM_URL = "http://localhost:8080/SummaryTask4/controller?command=confirmRegistration&ID=";
	
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
			InternetAddress internetAdressFrom = new InternetAddress("example@gmail.com");
			InternetAddress internetAdressTo = new InternetAddress(user.getUserEmail());
			
			message.setFrom(internetAdressFrom);
			message.addRecipient(Message.RecipientType.TO, internetAdressTo);
			message.setSentDate(new Date());
			
			if (user.getUserLanguage().equals("ru")) {
				setContentToConfirmationMessageRu(message, user);
			} else {
				setContentToConfirmationMessageEn(message, user);
			}			
			message.setSentDate(new Date());			
		} catch (AddressException e) {
			LOG.error(e);
		} catch (MessagingException e) {
			LOG.error(e);
		} catch (UnsupportedEncodingException e) {
			LOG.error(e);
		}		 
	}
	
	private static void setContentToConfirmationMessageRu(Message message, User user){
		message.setSubject(CONFIRM_RU);

		Multipart multipart = new MimeMultipart();

		String md5HexCipher = DigestUtils.md5Hex(user.getUserEmail());
		
		/*
		tring encodedEmai = new String(Base64.getEncoder().encode(
				user.getEmail().getBytes(StandardCharsets.UTF_8)));
		*/

		InternetHeaders emailAndPass = new InternetHeaders();
		emailAndPass.addHeader("Content-type", "text/plain; charset=UTF-8");
		String hello = "Здравствуйте, " +  user.getUserPassSurname()+ " "
				+ user.getUserPassName() + " " + user.getUserPassPatronomic() + " !\n"
				+ "Вы успешно прошли регистрацию на нашем сайте компании по прокату авто \"Юпитер\".\n\n\n";

		/*
		String data = "\nВаш почтовый адрес: " + user.getUserEmail() + "\nВаш пароль: "
				+ user. + "\n\n";
		*/

		MimeBodyPart greetingAndData = new MimeBodyPart(emailAndPass, (hello).getBytes("UTF-8"));

		InternetHeaders headers = new InternetHeaders();
		headers.addHeader("Content-type", "text/html; charset=UTF-8");
		String confirmLink = "Подтвердите регистрацию кликнув по этой"
				+ "<a href='" + CONF + encodedEmail + "'>ссылке</a>";
		MimeBodyPart link = new MimeBodyPart(headers,
				confirmLink.getBytes(StandardCharsets.UTF_8));

		multipart.addBodyPart(greetingAndData);
		multipart.addBodyPart(link);

		msg.setContent(multipart);
	}
	
	private static void setContentToConfirmationMessageEn(Message message, User user){
		
	}
}
