package com.wizz.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Deal implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer dealId;

    private Integer dealerId;

    private String dealContent;

    private Integer dealAdviceId;

}
