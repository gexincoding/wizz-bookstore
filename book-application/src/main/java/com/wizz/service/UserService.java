package com.wizz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wizz.common.ResponseResult;
import com.wizz.entity.User;

public interface UserService extends IService<User> {
    ResponseResult login(User user);


}
