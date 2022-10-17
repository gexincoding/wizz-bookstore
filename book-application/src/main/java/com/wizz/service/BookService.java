package com.wizz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wizz.dao.Book;

import java.util.List;


/**
 * @author xialinrui
 */
public interface BookService extends IService<Book> {

    /**
     * 添加新书籍
     *
     * @param book
     */
    void add(Book book);

    /**
     * 批量删除书籍
     * @param ids
     */
    void delete(List<Long> ids);
}
