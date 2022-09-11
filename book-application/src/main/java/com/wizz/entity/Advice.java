package com.wizz.entity;

import lombok.Data;

@Data
public class Advice {
    private Integer adviceId;
    private Integer adviserId;
    private String adviceContent;
    private Integer adviceStatus;
    private Integer adviceBookId;


}
