package com.wizz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wizz.entity.Book;
import com.wizz.entity.ResponseResult;
import com.wizz.vo.BookSearchVo;
import com.wizz.vo.CategoryVo;


public interface BookService extends IService<Book> {

    ResponseResult<Page<Book>> getBooksByContent(BookSearchVo bookSearchVo);
    ResponseResult<Page<Book>> getBooksByCategoryName(CategoryVo categoryVo);

}
