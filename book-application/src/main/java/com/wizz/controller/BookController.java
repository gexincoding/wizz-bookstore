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
     * 根据书名模糊查询书籍
     * @param page
     * @param pageSize
     * @param content
     * @return
     */
    @GetMapping("/list/content")
    public ResponseResult<Page<BookDto>> searchBooksByContent(int page, int pageSize, String content) {
        Page<BookDto> pageInfo = new Page<>(page, pageSize);
        List<BookDto> bookDto = bookService.getBooksByContent(content);
        pageInfo.setRecords(bookDto);
        return new ResponseResult<>(200, pageInfo);
    }

    /**
     * 根据分类查询书籍
     * @param categoryVo
     * @return
     */
    @GetMapping("/list")
    public ResponseResult<Page<BookDto>> searchBooksByCategoryId(int page, int pageSize, Long categoryId) {
        Page<BookDto> pageInfo = new Page<>(page, pageSize);
        List<BookDto> bookDto = bookService.getBooksByCategoryId(categoryId);
        pageInfo.setRecords(bookDto);
        return new ResponseResult<>(200, pageInfo);
    }





    //管理员批量添加书籍
    @PostMapping("/add")
    public ResponseResult addBook(@RequestBody List<BookDetailedInfoVo> bookDetailedInfoVoList) {
        int count = 0;
        for (BookDetailedInfoVo bookDetailedInfoVo : bookDetailedInfoVoList) {
            if (bookDetailedInfoVo.getBookISBN() == null) {
                continue;
            }
            count++;
            //根据ISBN到后台查询书籍情况
            QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("book_ISBN", bookDetailedInfoVo.getBookISBN());
            Book book = bookService.getOne(queryWrapper);
            if (book != null) {//如果库存中有书籍，直接数量++
                bookService.updateBookNumbersByISBN(book.getBookISBN());
            } else {//否则，新建书籍
                bookService.insertBookByBookDetailedInfoVo(bookDetailedInfoVo);
            }
        }
        return new ResponseResult(200, "添加成功～共成功添加" + count + "本书～");
    }
}
