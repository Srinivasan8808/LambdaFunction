package com.srinivasan.springAdvanced.service;

import com.srinivasan.springAdvanced.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public List<User> getAllUsers() {
        return List.of(
                new User(1, "Alice", "alice@example.com"),
                new User(2, "Bob", "bob@example.com"),
                new User(3, "Charlie", "charlie@example.com")
        );
    }
}
