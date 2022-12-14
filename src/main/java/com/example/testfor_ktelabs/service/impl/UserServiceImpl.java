package com.example.testfor_ktelabs.service.impl;

import com.example.testfor_ktelabs.entity.User;
import com.example.testfor_ktelabs.repositories.UserRepository;
import com.example.testfor_ktelabs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(Long userId, double firstDiscount, double secondDiscount) {
        userRepository.findById(userId).ifPresent(user -> {
            Optional.ofNullable(firstDiscount).ifPresent(user::setFirstDiscount);
            Optional.ofNullable(secondDiscount).ifPresent(user::setSecondDiscount);
            userRepository.save(user);
        });
    }
}
