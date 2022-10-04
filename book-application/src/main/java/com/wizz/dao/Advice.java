package com.wizz.dao;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Advice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long adviceId;

    private Integer adviserId;

    private String adviceContent;

    private Integer adviceStatus;

    private Integer adviceBookId;

}
