package com.wizz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.wizz.dao.LoginUser;
import com.wizz.dao.User;
import com.wizz.mapper.MenuMapper;
import com.wizz.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 自定义SpringSecurity进行用户校验的查询功能（从数据库中查询用户名和密码）
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);
        //如果没有查询到用户就抛出异常
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或者密码错误");
        }
        List<String> list = menuMapper.selectPermsByUserId(user.getUserId());
        //把数据封装成UserDetails返回
        return new LoginUser(user, list);
    }
}
