package com.wizz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wizz.dao.ResponseResult;
import com.wizz.dao.User;

public interface UserService extends IService<User>{

    ResponseResult login(User user);

    ResponseResult logout();


    void changePasswordByUsernameAndNewPassword(String username, String encode);


}
