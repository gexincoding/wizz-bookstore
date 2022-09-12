package com.wizz;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wizz.entity.User;
import com.wizz.mapper.MenuMapper;
import com.wizz.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
public class Test {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @org.junit.jupiter.api.Test
    public void BCryptPasswordEncoderTest() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("_Xlr20030526");
        System.out.println(encode);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        User user = userMapper.selectOne(queryWrapper.eq("username", "xialinrui"));
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
    }

    @org.junit.jupiter.api.Test
    public void SelectPermsByUserIdTest() {
        List<String> strings = menuMapper.selectPermsByUserId(1L);
        System.out.println(strings);
    }

    public static void main(String[] args) {

    }
}
