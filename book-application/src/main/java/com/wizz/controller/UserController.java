package com.wizz.controller;

import com.wizz.dao.*;
import com.wizz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult logout() {
        return userService.logout();
    }

    @PutMapping("/change/password")
    public ResponseResult changePassword(@RequestBody User user) {
        LoginUser currentUserDetails = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = currentUserDetails.getUsername();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userService.changePasswordByUsernameAndNewPassword(username, passwordEncoder.encode(user.getPassword()));
        return new ResponseResult(200, "修改密码成功，请重新登录～");
    }

}
