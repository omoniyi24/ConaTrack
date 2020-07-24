package com.jayjav.coronavirustracker.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);

    @Value("${com.jayjav.conatrack.email.subject}")
    private String EMAIL_SUBJECT;

    @Value("${com.jayjav.conatrack.email.body}")
    private String EMAIL_BODY;

    @Autowired
    private JavaMailSender sender;

    public boolean sendReport(String toAddress, String filePath){
        LOGGER.info("Inside sendReport()");
        boolean mailSent;
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setTo(toAddress);
            messageHelper.setSubject(EMAIL_SUBJECT);
            messageHelper.setText(EMAIL_BODY);
            messageHelper.addAttachment("Case Report", new File(filePath));
            System.out.println(">>> Before Sending Mail >>");
            sender.send(message);
            mailSent = true;
            LOGGER.info(">>> Mail Sent Succesffuly to >> {}", toAddress);
        } catch (MessagingException e) {
            LOGGER.error("Exception inside sendItinerary {}", e);
            mailSent = false;
        }
        return  mailSent;
    }
}
