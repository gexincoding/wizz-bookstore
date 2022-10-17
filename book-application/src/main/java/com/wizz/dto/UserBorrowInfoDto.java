package com.wizz.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author xialinrui
 */
@Data
public class UserBorrowInfoDto {
    private String name;
    private String phone;
    private String dept;
    private String book;
    private String imageUrl;
    private Date date;
}
