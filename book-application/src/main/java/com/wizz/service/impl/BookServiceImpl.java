package com.wizz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wizz.dto.BookDto;
import com.wizz.entity.Book;
import com.wizz.entity.ResponseResult;
import com.wizz.mapper.BookMapper;
import com.wizz.service.BookService;
import com.wizz.service.UserService;
import com.wizz.vo.BookSearchVo;
import com.wizz.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    private UserService userService;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public ResponseResult<Page<BookDto>> getBooksByContent(BookSearchVo bookSearchVo) {
        Page<Book> page = new Page<>(bookSearchVo.getPage(), bookSearchVo.getPageSize());
        bookMapper.getBookByContent(page, bookSearchVo.getContent());
        return new ResponseResult(200, page);
    }

    @Override
    public ResponseResult<Page<BookDto>> getBooksByCategoryId(CategoryVo categoryVo) {
        Page<Book> page = new Page<>(categoryVo.getPage(), categoryVo.getPageSize());
        bookMapper.getBookByCategoryId(page, categoryVo.getCategoryId());
        return new ResponseResult(200, page);
    }

    @Override
    public Book getBookByBookName(String bookName) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("book_name",bookName);
        Book book = bookMapper.selectOne(queryWrapper);
        return book;
    }

    @Override
    public Integer getBookStatusIdByBookName(String bookName) {
        return getBookByBookName(bookName).getBookStatusId();
    }


}
