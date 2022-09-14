package com.wizz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wizz.dto.BookDto;
import com.wizz.entity.Book;
import com.wizz.entity.ResponseResult;
import com.wizz.vo.BookSearchVo;
import com.wizz.vo.CategoryVo;


public interface BookService extends IService<Book> {

    ResponseResult<Page<BookDto>> getBooksByContent(BookSearchVo bookSearchVo);
    ResponseResult<Page<BookDto>> getBooksByCategoryId(CategoryVo categoryVo);

    Book getBookByBookName(String bookName);
    Integer getBookStatusIdByBookName(String bookName);

}
