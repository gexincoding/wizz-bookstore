package com.wizz.dto;

import com.wizz.entity.Book;
import lombok.Data;

import java.io.Serializable;

@Data
public class BookStatusDto extends Book implements Serializable {

    private String bookStatusName;

}
