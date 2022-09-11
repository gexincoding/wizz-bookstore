package com.wizz.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Advice implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer adviceId;

    private Integer adviserId;

    private String adviceContent;

    private Integer adviceStatus;

    private Integer adviceBookId;

}
