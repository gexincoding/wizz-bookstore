package com.wizz.dao;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    private String username;
    private String password;
    private String phoneNumber;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long deptId;
    private String realName;
}
