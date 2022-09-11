package com.wizz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wizz.common.ResponseResult;
import com.wizz.entity.User;
import com.wizz.mapper.UserMapper;
import com.wizz.service.UserService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public ResponseResult login(User user) {
        return null;
    }
}
