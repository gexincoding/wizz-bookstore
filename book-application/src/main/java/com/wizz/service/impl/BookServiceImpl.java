package com.wizz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wizz.dao.Book;
import com.wizz.mapper.BookMapper;
import com.wizz.service.BookService;

import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {


}
