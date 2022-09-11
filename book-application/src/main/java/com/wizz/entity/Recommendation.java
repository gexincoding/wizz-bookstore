package com.wizz.entity;

import lombok.Data;

@Data
public class Recommendation {
    private Integer recommenderId;
    private Integer recommendBookId;
    private Integer recommendReason;
}
