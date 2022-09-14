package com.wizz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wizz.dto.BookDto;
import com.wizz.dto.BookStatusDto;
import com.wizz.entity.ResponseResult;
import com.wizz.service.BookService;
import com.wizz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private UserService userService;

    @Resource
    private BookService bookService;

    @GetMapping("/status/list")
    public ResponseResult<Page<BookStatusDto>> getBookStatusList(String page, String pageSize){
        Page<BookStatusDto> pageInfo = new Page<>();
        List<BookStatusDto> bookStatusDtoList =  bookService.getBookStatusList();
        pageInfo.setRecords(bookStatusDtoList);
        return new ResponseResult(200,pageInfo);
    }

}
