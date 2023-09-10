package com.flagcamp.ehub.controller;

import com.flagcamp.ehub.exception.UserAlreadyExistException;
import com.flagcamp.ehub.model.User;
import com.flagcamp.ehub.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void addUser(@RequestBody User user) throws UserAlreadyExistException {
        userService.add(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user){
        return userService.loginAndGenerateToken(user);
    }
}
