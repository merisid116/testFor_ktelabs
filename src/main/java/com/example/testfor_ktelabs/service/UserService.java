package com.example.testfor_ktelabs.service;

import com.example.testfor_ktelabs.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    void updateUser(Long userId, double firstDiscount, double secondDiscount);
}
