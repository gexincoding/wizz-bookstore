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

import java.util.List;

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

    @GetMapping("/search/category")
    public ResponseResult<Page<BookDto>> searchBooksByCategoryName(@RequestBody CategoryVo categoryVo) {
        return bookService.getBooksByCategoryName(categoryVo);
    }


    @PostMapping("/recommend")
    public ResponseResult recommend(@RequestBody RecommendationVo recommendationVo) {
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.eq("book_name", recommendationVo.getBookName());
        Book book = bookService.getOne(bookQueryWrapper);
        if (book != null && book.getBookStatusId() != 1) {
            return new ResponseResult(401, "书架中已经有这本书了哦！快去看看吧～");
        }
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("category_name", recommendationVo.getCategoryName());
        Category category = categoryService.getOne(categoryQueryWrapper);
        if (category == null) {
            return new ResponseResult(401, "没有这个分类哦");
        }
        LoginUser currentUserDetails = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = currentUserDetails.getUsername();
        recommendationVo.setUsername(username);
        bookService.addRecommendationInfoByRecommendationVo(recommendationVo);
        return new ResponseResult(200, "推荐成功");
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
