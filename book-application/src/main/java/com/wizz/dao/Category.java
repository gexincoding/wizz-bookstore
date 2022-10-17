package com.wizz.dao;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xialinrui
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
}
