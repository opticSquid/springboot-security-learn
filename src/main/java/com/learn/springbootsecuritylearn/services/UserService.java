package com.learn.springbootsecuritylearn.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.learn.springbootsecuritylearn.models.User;
import com.learn.springbootsecuritylearn.repo.UserRepository;

@Service
public class UserService {
    List<User> list = new ArrayList<>();
    private UserRepository userRepo;
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepository;
        this.passwordEncoder = passwordEncoder;
        // need to append "ROLE_" prefix in roles for spring security
        userRepo.save(new User("soumalya", this.passwordEncoder.encode("1234"), "abc@gmail.com", "ROLE_normal_user"));
        userRepo.save(new User("chetna", this.passwordEncoder.encode("5678"), "xyz@gmail.com", "ROLE_admin_user"));
    }

    public List<User> getAllUsers() {
        return this.userRepo.findAll();
    }

    public User getUserbyId(String usrname) {
        return this.userRepo.findByUsername(usrname);
    }

    public User addUser(User user) {
        this.userRepo.save(user);
        return user;
    }
}
