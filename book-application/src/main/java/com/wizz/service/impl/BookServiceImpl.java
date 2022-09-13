package com.wizz.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wizz.dto.BookDto;
import com.wizz.dto.BookStatusDto;
import com.wizz.entity.Book;
import com.wizz.entity.ResponseResult;
import com.wizz.mapper.BookMapper;
import com.wizz.service.BookService;
import com.wizz.vo.BookSearchVo;
import com.wizz.vo.CategoryVo;
import com.wizz.vo.SingleBookRequestVo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public ResponseResult<Page<BookDto>> getBooksByContent(BookSearchVo bookSearchVo) {
        Page<Book> page = new Page<>(bookSearchVo.getPage(), bookSearchVo.getPageSize());
        bookMapper.getBookByContent(page, bookSearchVo.getContent());
        return new ResponseResult(200, page);
    }

    @Override
    public ResponseResult<Page<BookDto>> getBooksByCategoryName(CategoryVo categoryVo) {
        Page<Book> page = new Page<>(categoryVo.getPage(), categoryVo.getPageSize());
        bookMapper.getBookByCategoryName(page, categoryVo.getCategoryName());
        return new ResponseResult(200, page);
    }

    @Override
    public BookStatusDto getBookStatusBySingleBookRequestVo(SingleBookRequestVo singleBookRequestVo) {
        String bookName = singleBookRequestVo.getBookName();
        String isbn = singleBookRequestVo.getISBN();
        if (isbn != null && isbn != "") {
            return bookMapper.getBookStatusListByISBN(isbn);
        }
        return bookMapper.getBookStatusListByBookName(bookName);
    }

    @Override
    public void changeStatusToNoLeft(String bookName) {
       bookMapper.updateStatusNameByBookName(bookName);
    }

    @Override
    public void borrowOneUpdate(String bookName) {
        bookMapper.updateBookNumberStatusByBookName(bookName);
    }


}
