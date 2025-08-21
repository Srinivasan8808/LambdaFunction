package com.srinivasan.springAdvanced.controller;

import com.srinivasan.springAdvanced.model.User;
import com.srinivasan.springAdvanced.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to Spring Advanced App!";
    }
}
