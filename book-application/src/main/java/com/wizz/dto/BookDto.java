package com.wizz.dto;

import com.wizz.entity.Book;
import lombok.Data;

import java.io.Serializable;

@Data
public class BookDto extends Book implements Serializable {

    private String bookCategoryName;

    private String bookStatusName;

}
