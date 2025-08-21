package com.srinivasan.springCrud.service;

import com.srinivasan.springCrud.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();

    // CREATE
    public User addUser(User user) {
        users.add(user);
        return user;
    }

    // READ ALL
    public List<User> getAllUsers() {
        return users;
    }

    // READ ONE
    public User getUserById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // UPDATE
    public User updateUser(int id, User updatedUser) {
        for (User user : users) {
            if (user.getId() == id) {
                user.setName(updatedUser.getName());
                user.setEmail(updatedUser.getEmail());
                return user;
            }
        }
        return null;
    }

    // DELETE
    public void deleteUser(int id) {
        users.removeIf(user -> user.getId() == id);
    }
}
