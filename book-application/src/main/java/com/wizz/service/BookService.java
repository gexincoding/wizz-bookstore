package com.wizz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wizz.entity.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookService extends IService<Book> {
}
