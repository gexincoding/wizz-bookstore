package com.wizz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wizz.dao.Book;
import com.wizz.vo.BorrowInfoVo;

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

    /**
     * 查询用户是否有某本书的借阅信息
     * @param bookId
     * @param userId
     * @return
     */
    BorrowInfoVo getBorrowInfo(Long bookId, Long userId);

    void borrow(Book book, Long userId);

    void returnBook(Book book, Long userId);

}
