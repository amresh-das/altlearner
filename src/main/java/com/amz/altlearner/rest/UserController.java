package com.amz.altlearner.rest;

import com.amz.altlearner.data.entity.Login;
import com.amz.altlearner.data.entity.User;
import com.amz.altlearner.data.repos.LoginRepo;
import com.amz.altlearner.data.repos.UserRepo;
import com.amz.altlearner.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserRepo userRepo;
    private final LoginRepo loginRepo;
    private final EmailService emailService;
    private final String emailRecipient;

    public UserController(UserRepo userRepo, final LoginRepo loginRepo, final EmailService emailService, @Value("${email.sender}") final String emailSender) {
        this.userRepo = userRepo;
        this.loginRepo = loginRepo;
        this.emailService = emailService;
        this.emailRecipient = emailSender;
    }

    @PostMapping("login")
    public String login(@RequestBody final User user) {
        final User existing = userRepo.get(user.getEmail());
        if (existing == null) {
            userRepo.save(user);
            emailService.sendEmail(emailRecipient, "AltLearner: New User signed in", getUserDetails(user, Instant.now().toString()));
        }
        loginRepo.save(new Login(user.getEmail() + "-" + UUID.randomUUID(), Instant.now().toString()));
        return UUID.randomUUID().toString();
    }

    private String getUserDetails(final User user, final String time) {
        return String.format("<h1>New User Logged In</h1><table>" +
                "<thead><tr><th>Name</th><th>Email</th><th>Provider</th><th>Time</th></tr></thead>" +
                "<tbody><tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr></tbody>" +
                "</table>", user.getName(), user.getEmail(), user.getProvider(), time);
    }

}
