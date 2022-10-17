package com.wizz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wizz.dao.Book;
import com.wizz.dao.ResponseResult;
import com.wizz.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xialinrui
 */
@RestController
@Slf4j
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;

    /**
     * 添加新图书
     *
     * @param book
     * @return
     */
    @PostMapping("/add")
    public ResponseResult add(@RequestBody Book book) {
        book.setRest(1);
        book.setLent(0);
        bookService.add(book);
        return new ResponseResult(200, "添加成功");
    }

    /**
     * 增加书籍库存
     *
     * @param id
     * @param numbers
     * @return
     */
    @PutMapping("/increase/numbers")
    public ResponseResult increase(@RequestParam Long id, @RequestParam Integer numbers) {
        Book book = bookService.getById(id);
        book.setRest(numbers + book.getRest());
        bookService.save(book);
        return new ResponseResult(200, "更新成功");
    }


    /**
     * 减少书籍库存
     *
     * @param id
     * @param numbers
     * @return
     */
    @PutMapping("/decrease/numbers")
    public ResponseResult decrease(@RequestParam Long id, @RequestParam Integer numbers) {
        Book book = bookService.getById(id);
        Integer left = book.getRest() - numbers;
        if (left <= 0) {
            return new ResponseResult(400, "设置无效");
        }
        book.setRest(left);
        bookService.save(book);
        return new ResponseResult(200, "更新成功");
    }


    /**
     * 删除书籍
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseResult delete(@RequestParam List<Long> ids) {
        bookService.delete(ids);
        return new ResponseResult(200, "删除成功");
    }

    /**
     * 搜索框查询书籍
     *
     * @param content
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/list/like")
    public ResponseResult<Page<Book>> listLike(@RequestParam String content, @RequestParam Integer page, @RequestParam Integer pageSize) {
        Page<Book> resPage = new Page<>(page, pageSize);
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.like("name", content).orderByDesc("update_time");
        bookService.page(resPage, bookQueryWrapper);
        return new ResponseResult(200, "查询成功", resPage);
    }

    /**
     * 根据isbn查询图书详细信息
     *
     * @param isbn
     * @return
     */
    @GetMapping("/isbn")
    public ResponseResult<Book> isbn(@RequestParam String isbn) {
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.eq("isbn", isbn);
        Book book = bookService.getOne(bookQueryWrapper);
        return new ResponseResult(200, "查询成功", book);
    }
}
