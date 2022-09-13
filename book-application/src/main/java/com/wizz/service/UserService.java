package com.wizz.service;


import com.wizz.entity.ResponseResult;
import com.wizz.entity.User;

public interface UserService {

    ResponseResult login(User user);

    ResponseResult logout();


    void borrowBookByBookName(String bookName,String username);
}
