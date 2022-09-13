package com.wizz.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wizz.entity.Book;
import com.wizz.entity.Category;
import com.wizz.entity.ResponseResult;
import com.wizz.mapper.BookMapper;
import com.wizz.service.BookService;
import com.wizz.vo.BookSearchVo;
import com.wizz.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public ResponseResult<Page<Book>> getBooksByContent(BookSearchVo bookSearchVo) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("book_name", bookSearchVo.getContent());
        Page<Book> page = new Page<>(bookSearchVo.getPage(), bookSearchVo.getPageSize());
        bookMapper.selectPage(page, queryWrapper);
        return new ResponseResult(200, page);
    }

    @Override
    public ResponseResult<Page<Book>> getBooksByCategoryName(CategoryVo categoryVo) {
        Page<Book> page = new Page<>(categoryVo.getPage(), categoryVo.getPageSize());
        bookMapper.getBookByCategoryName(page, categoryVo.getCategoryName());
        return new ResponseResult(200, page);
    }


}
