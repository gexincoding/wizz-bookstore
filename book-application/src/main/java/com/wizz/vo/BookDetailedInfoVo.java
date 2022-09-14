package com.wizz.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookDetailedInfoVo implements Serializable {
    private String bookName;
    private String author;
    private String publisher;
    private String bookISBN;
}
