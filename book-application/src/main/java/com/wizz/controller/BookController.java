package com.wizz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wizz.dto.BookDto;
import com.wizz.entity.Book;
import com.wizz.entity.Category;
import com.wizz.entity.LoginUser;
import com.wizz.entity.ResponseResult;
import com.wizz.service.BookService;
import com.wizz.service.CategoryService;
import com.wizz.vo.BookSearchVo;
import com.wizz.vo.CategoryVo;
import com.wizz.vo.RecommendationVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/search/content")
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
        if (book != null) {
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

}
