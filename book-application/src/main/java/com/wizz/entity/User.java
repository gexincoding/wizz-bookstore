package com.wizz.entity;

import lombok.Data;

@Data
public class User {
    private Integer userId;
    private String userNickname;
    private Integer userStatus;
    private String userPassword;
}
