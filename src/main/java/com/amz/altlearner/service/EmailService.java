package com.amz.altlearner.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class EmailService {
    private final AmazonSimpleEmailService emailService;
    private final String emailSender;

    public EmailService(final AmazonSimpleEmailService emailService, @Value("${email.sender}") final String emailSender) {
        this.emailService = emailService;
        this.emailSender = emailSender;
    }

    public void sendEmail(final String recipient, final String subject, final String body) {
        final int timeout = 3000;
        final SendEmailRequest request = new SendEmailRequest()
                .withDestination(destination(recipient))
                .withMessage(new Message().withBody(new Body().withHtml(content(body)))
                        .withSubject(content(subject)))
                .withSource(emailSender).withSdkRequestTimeout(timeout);
        emailService.sendEmail(request);
    }

    private Content content(String body) {
        return new Content().withCharset(StandardCharsets.UTF_8.name()).withData(body);
    }

    private Destination destination(String recipient) {
        return new Destination().withToAddresses(recipient);
    }


}
