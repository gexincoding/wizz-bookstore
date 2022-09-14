package com.wizz.dto;

import com.wizz.entity.Book;
import lombok.Data;

@Data
public class BookDto extends Book {

    private String bookCategoryName;

    private String bookStatusName;

}
