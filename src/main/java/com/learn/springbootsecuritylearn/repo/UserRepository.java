package com.learn.springbootsecuritylearn.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.springbootsecuritylearn.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public User findByUsername(String username);
}
