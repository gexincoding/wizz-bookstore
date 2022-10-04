package com.wizz;


import com.wizz.mapper.MenuMapper;
import com.wizz.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
public class Test {


    @Value("${rootPath}")
    private String rootPath;


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @org.junit.jupiter.api.Test
    public void BCryptPasswordEncoderTest() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.matches("123456", "$2a$10$XOGpU6veVbzOGY/iuWTCceLAC7pwrJxIgk7NRI4SsIXb6tqv4/nP2"));

    }

    @org.junit.jupiter.api.Test
    public void SelectPermsByUserIdTest() {
        List<String> strings = menuMapper.selectPermsByUserId(1L);
        System.out.println(strings);
    }


    @org.junit.jupiter.api.Test
    public void testPath() {
        System.out.println(rootPath);
    }

    public static void main(String[] args) {


    }
}
