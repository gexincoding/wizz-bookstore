package com.wizz.controller;

import com.wizz.common.ResponseResult;
import com.wizz.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/book")
public class BookController {

    @GetMapping("/get/books/by/content")
    public ResponseResult<Set<Book>> getBooksByContent(@RequestParam("content") String content){
        return null;
    }

}
