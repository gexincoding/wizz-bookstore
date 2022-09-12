package com.wizz.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wizz.common.ResponseResult;
import com.wizz.entity.Book;
import com.wizz.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/page")
    public ResponseResult<Page> getBookPage(int page, int pageSize, String name){
        Page<Book> pageInfo = new Page<>();
        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name!=null, Book::getBookName, name);
        queryWrapper.orderByAsc(Book::getBookName);
        bookService.page(pageInfo, queryWrapper);
        return new ResponseResult(400,pageInfo);
    };

    @GetMapping("/list")
    public ResponseResult<List<Book>> getBookList(Book book){
        //获取book的categoryId,并查询数据库
        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Book::getBookCategoryId, book.getBookCategoryId());
        queryWrapper.orderByAsc(Book::getBookName);
        List<Book> list = bookService.list(queryWrapper);
        return new ResponseResult(400,list);
    }


}
