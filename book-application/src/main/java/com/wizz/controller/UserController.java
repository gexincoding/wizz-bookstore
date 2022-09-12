package com.wizz.controller;

import com.wizz.entity.Book;
import com.wizz.entity.LoginUser;
import com.wizz.entity.ResponseResult;
import com.wizz.entity.User;
import com.wizz.service.UserService;
import com.wizz.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult logout() {
        return userService.logout();
    }

    @GetMapping("/borrow")
    public ResponseResult<User> getBooksByContent(@RequestParam("bookId") Long bookId, @RequestParam("userId") Long userId) {
        return null;
    }

    @RequestMapping("/return/list")
    public ResponseResult getToReturnList(){
        LoginUser currentUserDetails = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = currentUserDetails.getUsername();
        return null;
    }

}
