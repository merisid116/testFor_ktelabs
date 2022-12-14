package com.example.testfor_ktelabs.controller;

import com.example.testfor_ktelabs.entity.User;
import com.example.testfor_ktelabs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public List<User> getAllUser() {
        return userService.findAll();
    }
    @PatchMapping
    public void updateUser(@RequestParam(value = "userId", required = true) Long userId,
                           @RequestParam(value = "firstDiscount", required = false) double firstDiscount,
                           @RequestParam(value = "secondDiscount", required = false) double secondDiscount) {
        userService.updateUser(userId, firstDiscount, secondDiscount);
    }
}
