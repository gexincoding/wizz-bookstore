package com.wizz.vo;

import com.wizz.entity.Book;
import lombok.Data;

@Data
public class BookNewVo extends Book {

    private String author;

    private String publisher;
}
