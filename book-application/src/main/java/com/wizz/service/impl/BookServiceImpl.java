package com.wizz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wizz.dao.Book;
import com.wizz.mapper.BookMapper;
import com.wizz.service.BookService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xialinrui
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {


    @Resource
    private BookMapper bookMapper;

    @Override
    public void add(Book book) {
        bookMapper.insert(book);
    }

    @Override
    public void delete(List<Long> ids) {
        bookMapper.deleteBatchIds(ids);
    }
}
