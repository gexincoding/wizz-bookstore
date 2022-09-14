package com.wizz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wizz.dto.BookDto;
import com.wizz.entity.*;
import com.wizz.service.BookService;
import com.wizz.service.CategoryService;
import com.wizz.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;


    /**
     * 根据用户输入查询书籍，返回分页信息
     *
     * @param bookSearchVo
     * @return
     */
    @GetMapping("/list/content")
    public ResponseResult<Page<BookDto>> searchBooksByContent(@RequestBody BookSearchVo bookSearchVo) {
        return bookService.getBooksByContent(bookSearchVo);
    }

    @GetMapping("/list")
    public ResponseResult<Page<BookDto>> searchBooksByCategoryName(@RequestBody CategoryVo categoryVo) {
        return bookService.getBooksByCategoryName(categoryVo);
    }





    //管理员批量添加书籍
    @PostMapping("/add")
    public ResponseResult addBook(@RequestBody List<BookNewVo> bookNewVos) {
        log.info(bookNewVos.toString());
//        for (BookDetailedInfoVo bookDetailedInfoVo : bookDetailedInfoVoList) {
//            //根据ISBN到后台查询书籍情况
//            QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("book_ISBN", bookDetailedInfoVo.getBookISBN());
//            Book book = bookService.getOne(queryWrapper);
//            if (book != null) {//如果库存中有书籍，直接数量++
//                bookService.updateBookNumbersByISBN(book.getBookISBN());
//            } else {//否则，新建书籍
//                bookService.insertBookByBookDetailedInfoVo(bookDetailedInfoVo);
//            }
//        }
        return new ResponseResult(200, "添加成功");
    }
}
