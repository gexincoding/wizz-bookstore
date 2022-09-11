package com.wizz.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String userNickname;

    private Integer userStatus;

    private String userPassword;
}
