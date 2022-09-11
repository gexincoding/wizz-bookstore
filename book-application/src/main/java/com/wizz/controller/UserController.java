package com.wizz.controller;

import com.wizz.common.ResponseResult;
import com.wizz.entity.Book;
import com.wizz.entity.User;
import com.wizz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public ResponseResult login(@RequestBody User user ){
        return userService.login(user);
    }



    @GetMapping("/borrow")
    public ResponseResult getBooksByContent(@RequestParam("bookId") Long bookId, @RequestParam("userId") Long userId){
        return null;
    }



}
