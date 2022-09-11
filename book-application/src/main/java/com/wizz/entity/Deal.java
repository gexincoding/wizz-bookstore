package com.wizz.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deal implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long dealId;

    private Integer dealerId;

    private String dealContent;

    private Integer dealAdviceId;

}
