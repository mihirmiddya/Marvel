package com.avengers.marvel.mail.service;

import com.avengers.marvel.mail.model.Mail;
import com.avengers.marvel.mail.repository.MailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static com.avengers.marvel.mail.constants.MailConstants.FROM_MAIL;

@Service
public class MailerService {

    private static final Logger logger = LoggerFactory.getLogger(MailerService.class);

    @Autowired
    private MailRepository mailRepository;

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String toAddress, String subject, String body) {
        Mail mail = new Mail(toAddress, subject, body);
        mailRepository.save(mail);
        logger.info("Mailer log added successfully.");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM_MAIL);
        message.setTo(toAddress);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        logger.info("Mail send successfully.");
    }

}
