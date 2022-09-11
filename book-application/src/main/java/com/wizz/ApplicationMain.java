package com.wizz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wizz.mapper")
public class ApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
    }
}
