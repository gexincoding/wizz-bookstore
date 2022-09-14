package com.wizz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wizz.dto.BookDto;
import com.wizz.dto.BookStatusDto;
import com.wizz.entity.Book;
import com.wizz.entity.ResponseResult;
import com.wizz.vo.*;

import java.util.List;


public interface BookService extends IService<Book> {

    List<BookDto> getBooksByContent(String content);

    ResponseResult<Page<BookDto>> getBooksByCategoryName(CategoryVo categoryVo);

    BookStatusDto getBookStatusBySingleBookRequestVo(SingleBookRequestVo singleBookRequestVo);

    void changeStatusToNoLeft(String bookName);

    void borrowOneUpdate(String bookName);

    void addRecommendationInfoByRecommendationVo(RecommendationVo recommendationVo);


    void updateBookNumbersByISBN(String bookISBN);

    void insertBookByBookDetailedInfoVo(BookDetailedInfoVo bookDetailedInfoVo);
}
