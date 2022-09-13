package com.wizz.vo;

import lombok.Data;

@Data
public class BookSearchVo {
    private int page;
    private int pageSize;
    private String content;
}
