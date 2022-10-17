package com.wizz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wizz.dao.Book;
import com.wizz.mapper.BookMapper;
import com.wizz.service.BookService;

import com.wizz.vo.BorrowInfoVo;
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

    @Override
    public BorrowInfoVo getBorrowInfo(Long bookId, Long userId) {
        return bookMapper.getBorrowInfo(bookId, userId);

    }

    @Override
    public void borrow(Book book, Long userId) {
        //生成借阅信息
        bookMapper.borrow(book.getId(), userId);
        //修改库存
        book.setLent(book.getLent() + 1);
        book.setRest(book.getRest() - 1);
        this.updateById(book);
    }

    @Override
    public void returnBook(Book book, Long userId) {
        //生成归还信息
        bookMapper.returnBook(book.getId(), userId);
        //修改库存
        book.setLent(book.getLent() - 1);
        book.setRest(book.getRest() + 1);
        this.updateById(book);
    }
}
