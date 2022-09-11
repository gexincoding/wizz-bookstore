package com.wizz.entity;

import lombok.Data;

@Data
public class Book {
    private Long bookId;
    private String bookName;
    private Integer bookCategoryId;
    private Integer bookBorrowerId;
    private Integer bookStatus;
    private String bookISBN;
    private Integer bookLeftNumbers;
    private String bookImage;
}
