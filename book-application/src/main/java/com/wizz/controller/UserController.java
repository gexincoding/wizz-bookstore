package com.wizz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wizz.dto.BookDto;
import com.wizz.dto.BookReturnDto;
import com.wizz.dto.BookStatusDto;
import com.wizz.entity.*;
import com.wizz.service.BookService;
import com.wizz.service.CategoryService;
import com.wizz.service.UserService;
import com.wizz.vo.BookVo;
import com.wizz.vo.ChangePasswordVo;
import com.wizz.vo.RecommendationVo;
import com.wizz.vo.SingleBookRequestVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private BookService bookService;

    private CategoryService categoryService;


    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult logout() {
        return userService.logout();
    }


    @GetMapping("/borrow")
    public ResponseResult getBooksByContent(@RequestBody SingleBookRequestVo singleBookRequestVo) {
        LoginUser currentUserDetails = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = currentUserDetails.getUsername();
        BookStatusDto bookStatusDto = bookService.getBookStatusBySingleBookRequestVo(singleBookRequestVo);
        //如果状态为已借空，返回借阅失败信息
        if (bookStatusDto.getBookStatusName().equals("已借空")) {
            return new ResponseResult(201, "书已经没有啦，下次再来～");
        }
        if (bookStatusDto.getBookLeftNumbers() == 1) {
            bookService.changeStatusToNoLeft(bookStatusDto.getBookName());
        }
        bookService.borrowOneUpdate(bookStatusDto.getBookName());
        userService.borrowBookByBookName(bookStatusDto.getBookName(), username);
        return new ResponseResult(200, "已经成功借阅哦～记得及时归还～");
    }

    @GetMapping("return/list")
    public ResponseResult<Page<BookReturnDto>> getToReturnList(@RequestBody BookVo bookVo) {
        LoginUser currentUserDetails = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = currentUserDetails.getUsername();
        bookVo.setUsername(username);
        return userService.getToReturnBooksByReturnBookVo(bookVo);
    }

    @GetMapping("favourites/list")
    public ResponseResult<Page<BookDto>> getFavouritesBooksList(@RequestBody BookVo bookVo) {
        LoginUser currentUserDetails = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = currentUserDetails.getUsername();
        bookVo.setUsername(username);
        return userService.getFavouritesBooksByBookVo(bookVo);
    }


    @RequestMapping("/change/password")
    public ResponseResult changePassword(@RequestBody ChangePasswordVo changePasswordVo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        queryWrapper.eq("username", changePasswordVo.getUsername());
        User user = userService.getOne(queryWrapper);
        if (user == null) {
            return new ResponseResult(400, "用户名不存在～");
        }
        if (!passwordEncoder.matches(changePasswordVo.getOldPassword(), user.getPassword())) {
            return new ResponseResult(400, "原始密码错误~");
        }
        userService.changePasswordByUsernameAndNewPassword(changePasswordVo.getUsername(), passwordEncoder.encode(changePasswordVo.getNewPassword()));
        return new ResponseResult(200, "修改密码成功，请重新登录～");

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


}
