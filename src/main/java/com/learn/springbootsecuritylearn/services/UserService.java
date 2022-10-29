package com.learn.springbootsecuritylearn.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.learn.springbootsecuritylearn.models.User;

@Service
public class UserService {
    List<User> list = new ArrayList<>();

    public UserService() {
        list.add(new User("abc", "abc", "abc@gmail.com"));
        list.add(new User("xyz", "abcxyz", "xyz@gmail.com"));
    }

    public List<User> getAllUsers() {
        return this.list;
    }

    public User getUserbyId(String usrname) {
        return this.list.stream().filter((user) -> user.getUsername().equals(usrname)).findAny().orElse(null);
    }

    public User addUser(User user) {
        this.list.add(user);
        return user;
    }
}
