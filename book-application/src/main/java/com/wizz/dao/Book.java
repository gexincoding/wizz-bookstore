package com.wizz.dao;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xialinrui
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
    private String isbn;
    private Integer rest;
    private Integer lent;
    private String imageUrl;
    private String publisher;
    private String author;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;
    private String introduction;
}
