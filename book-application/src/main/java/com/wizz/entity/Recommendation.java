package com.wizz.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recommendation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long recommenderId;

    private Integer recommendBookId;

    private Integer recommendReason;
}
