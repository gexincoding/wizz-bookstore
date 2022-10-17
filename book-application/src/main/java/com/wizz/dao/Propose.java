package com.wizz.dao;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author xialinrui
 */
@Data
public class Propose {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String content;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}
