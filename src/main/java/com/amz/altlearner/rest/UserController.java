package com.amz.altlearner.rest;

import com.amz.altlearner.data.entity.Login;
import com.amz.altlearner.data.entity.User;
import com.amz.altlearner.data.repos.LoginRepo;
import com.amz.altlearner.data.repos.UserRepo;
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

    public UserController(UserRepo userRepo, final LoginRepo loginRepo) {
        this.userRepo = userRepo;
        this.loginRepo = loginRepo;
    }

    @PostMapping("login")
    public void login(@RequestBody final User user) {
        final User existing = userRepo.get(user.getEmail());
        if (existing == null) {
            userRepo.save(user);
        }
        loginRepo.save(new Login(user.getEmail() + "-" + UUID.randomUUID(), Instant.now().toString()));
    }

}
