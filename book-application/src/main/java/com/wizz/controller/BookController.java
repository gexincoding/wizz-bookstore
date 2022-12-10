package com.wizz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wizz.dao.Book;
import com.wizz.dao.LoginUser;
import com.wizz.dao.ResponseResult;
import com.wizz.dao.User;
import com.wizz.dto.UserBorrowInfoDto;
import com.wizz.service.BookService;
import com.wizz.vo.BorrowInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
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
        bookQueryWrapper.like("name", content).orderByDesc("name");
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


    /**
     * 根据ID借书
     *
     * @param bookId
     * @return
     */
    @PutMapping("/borrow/id")
    public ResponseResult borrow(@RequestParam("id") Long bookId) {
        LoginUser currentUserDetails = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = currentUserDetails.getUser();
        BorrowInfoVo borrowInfoVo = bookService.getBorrowInfo(bookId, user.getUserId());
        //用户正在借阅这个书籍，提示用户不能重复借阅同一本书
        if (borrowInfoVo != null) {
            return new ResponseResult<>(400, "您已经借过该书啦～");
        }
        //否则 让用户借阅 库存-1
        Book book = bookService.getById(bookId);
        bookService.borrow(book, user.getUserId());
        return new ResponseResult<>(200, "借阅成功");
    }

    @PutMapping("/borrow/isbn")
    public ResponseResult borrow(@RequestParam String isbn) {
        LoginUser currentUserDetails = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = currentUserDetails.getUser();
        Book book = bookService.getOne(new QueryWrapper<Book>().eq("isbn", isbn));
        BorrowInfoVo borrowInfoVo = bookService.getBorrowInfo(book.getId(), user.getUserId());
        //用户正在借阅这个书籍，提示用户不能重复借阅同一本书
        if (borrowInfoVo != null) {
            return new ResponseResult<>(400, "您已经借过该书啦～");
        }
        //否则 让用户借阅 库存-1
        bookService.borrow(book, user.getUserId());
        return new ResponseResult<>(200, "借阅成功");
    }


    /**
     * 根据ID借书
     *
     * @param bookId
     * @return
     */
    @PutMapping("/return/id")
    public ResponseResult returnId(@RequestParam("id") Long bookId) {
        LoginUser currentUserDetails = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = currentUserDetails.getUser();
        BorrowInfoVo borrowInfoVo = bookService.getBorrowInfo(bookId, user.getUserId());
        //用户没有借阅这本书，直接返回
        if (borrowInfoVo == null) {
            return new ResponseResult<>(400, "您未借阅此书～");
        }
        //否则 让用户还书 库存+1
        Book book = bookService.getById(bookId);
        bookService.returnBook(book, user.getUserId());
        return new ResponseResult<>(200, "借阅成功");
    }


    @PutMapping("/return/isbn")
    public ResponseResult returnIsbn(@RequestParam String isbn) {
        LoginUser currentUserDetails = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = currentUserDetails.getUser();
        Book book = bookService.getOne(new QueryWrapper<Book>().eq("isbn", isbn));
        BorrowInfoVo borrowInfoVo = bookService.getBorrowInfo(book.getId(), user.getUserId());
        //用户没有借阅这本书，直接返回
        if (borrowInfoVo == null) {
            return new ResponseResult<>(400, "您未借阅此书～");
        }
        //否则 让用户还书 库存+1
        bookService.returnBook(book, user.getUserId());
        return new ResponseResult<>(200, "借阅成功");
    }


    /**
     * 根据分类id查询该分类的所有图书
     *
     * @param page
     * @param pageSize
     * @param categoryId
     * @return
     */
    @GetMapping("/list/category")
    public ResponseResult<Page<Book>> listCategory(@RequestParam Integer page, @RequestParam Integer pageSize, @RequestParam Long categoryId) {
        Page<Book> resPage = new Page<>(page, pageSize);
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.like("category_id", categoryId).orderByDesc("name");
        bookService.page(resPage, bookQueryWrapper);
        return new ResponseResult(200, "查询成功", resPage);
    }


    /**
     * 根据图书id查询正在借阅这本书的人
     * @param bookId
     * @return
     */
    @GetMapping("/borrower")
    public ResponseResult<List<UserBorrowInfoDto>> borrower(@RequestParam("id") Long bookId) {
        List<UserBorrowInfoDto> resList = bookService.getBorrower(bookId);
        return new ResponseResult<>(200, "查询成功", resList);
    }


    

}
