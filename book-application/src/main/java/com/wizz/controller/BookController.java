package com.wizz.controller;

import com.wizz.dao.Book;
import com.wizz.dao.ResponseResult;
import com.wizz.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author xialinrui
 */
@RestController
@Slf4j
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    @PostMapping("/add")
    public ResponseResult add(@RequestBody Book book) {
        return null;
    }

}
