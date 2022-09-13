package com.wizz.dto;

import lombok.Data;

@Data
public class BookStatusDto {
    private String bookName;
    private String bookStatusName;
    private Integer bookLeftNumbers;
    private Integer bookTotalNumbers;
    private Integer bookLentNumbers;
}
