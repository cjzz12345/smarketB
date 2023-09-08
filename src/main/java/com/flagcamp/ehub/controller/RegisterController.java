package com.flagcamp.ehub.controller;

import com.flagcamp.ehub.exception.UserAlreadyExistException;
import com.flagcamp.ehub.model.User;
import com.flagcamp.ehub.service.RegisterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public void addUser(@RequestBody User user) throws UserAlreadyExistException {
        registerService.add(user);
    }
}
