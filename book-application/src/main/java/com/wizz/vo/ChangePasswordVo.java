package com.wizz.vo;

import lombok.Data;

@Data
public class ChangePasswordVo {
    private String username;
    private String oldPassword;
    private String newPassword;
}
