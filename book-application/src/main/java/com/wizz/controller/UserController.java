package com.wizz.controller;

import com.wizz.dao.*;
import com.wizz.dto.UserInfoDto;
import com.wizz.service.UserService;
import com.wizz.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xialinrui
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource
    private RedisCache redisCache;

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
        User curUser = currentUserDetails.getUser();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        curUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.updateById(curUser);
        redisCache.deleteObject("login:" + curUser.getUserId());
        return new ResponseResult(200, "修改密码成功，请重新登录～");
    }

    @GetMapping("/details")
    public ResponseResult<UserInfoDto> details() {
        LoginUser currentUserDetails = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User curUser = currentUserDetails.getUser();
        UserInfoDto resUser = userService.getDetails(curUser.getUserId());
        return new ResponseResult<>(200, "查询成功", resUser);
    }

}
