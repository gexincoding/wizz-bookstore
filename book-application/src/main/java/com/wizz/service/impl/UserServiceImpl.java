package com.wizz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wizz.common.MyServiceException;
import com.wizz.entity.LoginUser;
import com.wizz.entity.User;
import com.wizz.mapper.UserMapper;
import com.wizz.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    public UserDetails loadUserByUsername(String username){

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserNickname, username);
        User user = this.getOne(queryWrapper);
        if(Objects.isNull(user)){
            throw new MyServiceException("账号或密码错误");
        }
        return new LoginUser(user);
    }
}
