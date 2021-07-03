package com.ecommerce.awf.service.mail.impl;

import com.ecommerce.awf.service.mail.EmailSenderServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class OrderConfirmation implements EmailSenderServices {

    private static final Logger logger = LogManager.getLogger(OrderConfirmation.class);
    private Session mailSession;

    @Value("${mail.email.from}")
    private String emailFrom;
    @Value("${mail.email.host}")
    private String emailHost;
    @Value("${mail.email.password}")
    private String emailPass;
    @Value("${mail.email.port}")
    private String emailPort;

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendEmail(String subject, String contactBody, String emailTo) {
        MimeMessage emailMessage = this.getMimeMessage(subject, contactBody, emailTo);
        try {
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(emailHost, emailFrom, emailPass);
            transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
            transport.close();
        } catch (MessagingException messagingException) {
            logger.error("Message cannot be send sendEmail()" + messagingException.getMessage());
        }
    }

    private MimeMessage getMimeMessage(String emailSubject, String emailBody, String emailTo) {
        Properties emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", emailPort);
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
        mailSession = Session.getDefaultInstance(emailProperties, null);
        MimeMessage emailMessage = null;
        try {
            emailMessage = new MimeMessage(mailSession);
            emailMessage.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(emailTo));
            emailMessage.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(emailFrom));
            emailMessage.setSubject(emailSubject);
            emailMessage.setContent(emailBody, "text/html; charset=utf-8");
        } catch (MessagingException messagingException) {
            logger.error("Message cannot be send getMimeMessage()" + messagingException.getMessage());
        }
        return emailMessage;

    }

}
