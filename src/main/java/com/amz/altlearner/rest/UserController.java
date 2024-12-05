package com.amz.altlearner.rest;

import com.amz.altlearner.data.entity.Login;
import com.amz.altlearner.data.entity.User;
import com.amz.altlearner.data.repos.LoginRepo;
import com.amz.altlearner.data.repos.UserRepo;
import com.amz.altlearner.service.CacheProvider;
import com.amz.altlearner.service.EmailService;
import com.amz.altlearner.service.IdTokenVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserRepo userRepo;
    private final LoginRepo loginRepo;
    private final EmailService emailService;
    private final IdTokenVerifier idTokenVerifier;
    private final CacheProvider cacheProvider;
    private final String emailRecipient;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public UserController(UserRepo userRepo, final LoginRepo loginRepo, final EmailService emailService,
                          final IdTokenVerifier idTokenVerifier, final CacheProvider cacheProvider,
                          @Value("${email.sender}") final String emailSender) {
        this.userRepo = userRepo;
        this.loginRepo = loginRepo;
        this.emailService = emailService;
        this.idTokenVerifier = idTokenVerifier;
        this.cacheProvider = cacheProvider;
        this.emailRecipient = emailSender;
    }

    @PostMapping("login")
    public void login(@RequestBody final User user, @RequestHeader("ticket") final String ticket) {
        final String email = user.getEmail();
        verifyTicket(user.getProvider(), ticket, email);
        final User existing = userRepo.get(email);
        if (existing == null) {
            userRepo.save(user);
            try {
                emailService.sendEmail(emailRecipient, "AltLearner: New User signed in", getUserDetails(user, Instant.now().toString()));
            } catch (final Exception e) {
                LOGGER.error("Exception sending email", e);
            }
        }
        loginRepo.save(new Login(email + "-" + UUID.randomUUID(), Instant.now().toString()));
        cacheProvider.getCache("AUTH").putIfAbsent(ticket, user);
    }

    private void verifyTicket(final String provider, final String ticket, final String email) {
        if (!idTokenVerifier.verify(provider, ticket, email)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Ticket Validation failed"
            );
        }
    }

    private String getUserDetails(final User user, final String time) {
        return String.format("<h1>New User Logged In</h1><table>" +
                "<thead><tr><th>Name</th><th>Email</th><th>Provider</th><th>Time</th></tr></thead>" +
                "<tbody><tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr></tbody>" +
                "</table>", user.getName(), user.getEmail(), user.getProvider(), time);
    }

}
