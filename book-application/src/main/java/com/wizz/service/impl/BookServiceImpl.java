package com.wizz.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wizz.dto.BookDto;
import com.wizz.dto.BookStatusDto;
import com.wizz.entity.Book;
import com.wizz.entity.ResponseResult;
import com.wizz.mapper.BookMapper;
import com.wizz.service.BookService;
import com.wizz.vo.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<BookDto> getBooksByContent(String content) {
        return bookMapper.getBookByContent(content);
    }

    @Override
    public List<BookDto> getBooksByCategoryId(Long categoryId) {
        return bookMapper.getBookByCategoryId(categoryId);
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


    @Override
    public void addRecommendationInfoByRecommendationVo(RecommendationVo recommendationVo) {
        bookMapper.addBook(recommendationVo.getBookName(),recommendationVo.getPublisher(),recommendationVo.getCategoryName());
        bookMapper.setRecommendation(recommendationVo.getUsername(),recommendationVo.getBookName(),recommendationVo.getReasons());

    }

    @Override
    public void updateBookNumbersByISBN(String bookISBN) {
        bookMapper.updateBookNumbersByISBN(bookISBN);
    }

    @Override
    public void insertBookByBookDetailedInfoVo(BookDetailedInfoVo bookDetailedInfoVo) {
        bookMapper.insertBook(bookDetailedInfoVo.getBookName(),bookDetailedInfoVo.getBookISBN(),bookDetailedInfoVo.getAuthor(),bookDetailedInfoVo.getPublisher());
    }

    @Override
    public List<BookStatusDto> getBookStatusList() {
        return  bookMapper.selectBookStatus();
    }
}
