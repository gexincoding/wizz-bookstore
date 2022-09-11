package com.wizz.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long bookId;

    private String bookName;

    private Integer bookCategoryId;

    private Integer bookBorrowerId;

    private Integer bookStatus;

    private String bookISBN;

    private Integer bookLeftNumbers;

    private String bookImage;

}
