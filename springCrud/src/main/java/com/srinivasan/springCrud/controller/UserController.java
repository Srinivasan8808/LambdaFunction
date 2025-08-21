package com.srinivasan.springCrud.controller;

import com.srinivasan.springCrud.model.User;
import com.srinivasan.springCrud.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }
    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id,@RequestBody User user){
        return userService.updateUser(id,user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return "User with id"+ id + "deleted";
    }
}
