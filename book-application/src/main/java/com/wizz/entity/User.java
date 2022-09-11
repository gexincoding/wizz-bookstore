package com.wizz.entity;

import lombok.Data;

@Data
public class User {
    private Long bookId;
    private String bookName;
    private Integer bookCategoryId;
    private Integer bookBorrowerId;
    private Integer bookStatus;
    private String bookISBN;

}
