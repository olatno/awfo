package com.ecommerce.awf.service.mail;

public interface EmailSenderServices {

    void sendEmail(String subject, String contactBody, String emailTo);
}
