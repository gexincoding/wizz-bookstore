package com.wizz.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Recommendation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer recommenderId;

    private Integer recommendBookId;

    private Integer recommendReason;
}
