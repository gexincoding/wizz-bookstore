package com.wizz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wizz.dto.BookDto;
import com.wizz.dto.BookStatusDto;
import com.wizz.entity.Book;
import com.wizz.entity.ResponseResult;
import com.wizz.vo.BookSearchVo;
import com.wizz.vo.CategoryVo;
import com.wizz.vo.RecommendationVo;
import com.wizz.vo.SingleBookRequestVo;


public interface BookService extends IService<Book> {

    ResponseResult<Page<BookDto>> getBooksByContent(BookSearchVo bookSearchVo);

    ResponseResult<Page<BookDto>> getBooksByCategoryName(CategoryVo categoryVo);

    BookStatusDto getBookStatusBySingleBookRequestVo(SingleBookRequestVo singleBookRequestVo);

    void changeStatusToNoLeft(String bookName);

    void borrowOneUpdate(String bookName);

    void addRecommendationInfoByRecommendationVo(RecommendationVo recommendationVo);


}
