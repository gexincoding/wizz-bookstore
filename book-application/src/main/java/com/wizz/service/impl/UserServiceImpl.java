package com.wizz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wizz.entity.User;
import com.wizz.mapper.UserMapper;
import com.wizz.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
