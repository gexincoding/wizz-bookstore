package com.wizz.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author xialinrui
 */
@Data
public class UserBorrowInfoDto {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    private String username;
    private String phone;
    private String deptName;
    private String book;
    private String imageUrl;
    private Date date;
}
