package com.wizz.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wizz.entity.Category;
import com.wizz.entity.ResponseResult;
import com.wizz.entity.Book;
import com.wizz.service.BookService;
import com.wizz.vo.BookSearchVo;
import com.wizz.vo.CategoryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;


    /**
     * 根据用户输入查询书籍，返回分页信息
     * @param bookSearchVo
     * @return
     */
    @GetMapping("/search/content")
    public ResponseResult<Page<Book>> searchBooksByContent(@RequestBody BookSearchVo bookSearchVo) {
        return bookService.getBooksByContent(bookSearchVo);
    }

    @GetMapping("/search/category")
    public ResponseResult<Page<Book>> searchBooksByCategoryName(@RequestBody CategoryVo categoryVo) {
        return bookService.getBooksByCategoryName(categoryVo);
    }


}
