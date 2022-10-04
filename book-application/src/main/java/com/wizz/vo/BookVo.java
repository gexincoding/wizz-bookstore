package com.wizz.vo;

import com.wizz.dao.Book;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BookVo extends Book {
    private MultipartFile image;
    private Integer numbers;
}
