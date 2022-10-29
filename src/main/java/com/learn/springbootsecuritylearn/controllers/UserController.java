package com.learn.springbootsecuritylearn.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.springbootsecuritylearn.models.User;
import com.learn.springbootsecuritylearn.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userservice;

    public UserController(UserService userService) {
        this.userservice = userService;
    }

    @GetMapping()
    public List<User> getUsers() {
        return this.userservice.getAllUsers();
    }

    @GetMapping("/{username}")
    public User getUserbyusrname(@PathVariable("username") String usrname) {
        return this.userservice.getUserbyId(usrname);
    }

    @PostMapping()
    public User add(@RequestBody User user) {
        this.userservice.addUser(user);
        return user;
    }

}
