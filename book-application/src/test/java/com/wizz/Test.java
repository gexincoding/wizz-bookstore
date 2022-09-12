package com.wizz;


import com.wizz.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class Test {

    @Autowired
    private UserMapper userMapper;

    @org.junit.jupiter.api.Test
    public void BCryptPasswordEncoderTest(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("_Xlr20030526");
        System.out.println(encode);
    }

    public static void main(String[] args) {

    }
}
