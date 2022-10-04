package com.wizz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wizz.dao.Book;
import com.wizz.dao.ResponseResult;
import com.wizz.service.BookService;
import com.wizz.vo.BookVo;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@RestController
@Slf4j
@RequestMapping("/book")
public class BookController {


    @Value("${rootPath}")
    private String rootPath;

    @Resource
    private BookService bookService;

    @PostMapping("/add")
    public ResponseResult addBooks(@RequestBody List<BookVo> books) {
        File fileDir = new File(rootPath);
        if (!fileDir.exists() && !fileDir.isDirectory()) {
            fileDir.mkdirs();
        }
        for (BookVo book : books) {
            //如果有这本书 直接添加库存即可
            QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
            bookQueryWrapper.eq("isbn", book.getIsbn());
            Book ifHas = bookService.getOne(bookQueryWrapper);
            if (ifHas != null) {
                ifHas.setRest(ifHas.getRest() + book.getNumbers());
                bookService.updateById(ifHas);
                continue;
            }
            String imageUrl = "";
            MultipartFile image = book.getImage();
            if (book.getImage() != null && !book.getImage().isEmpty()) {
                try {
                    imageUrl = rootPath + image.getOriginalFilename() + UUID.randomUUID();
                    image.transferTo(new File(imageUrl));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Book addBook = new Book(null, book.getName(), book.getIsbn(), book.getNumbers(), null, imageUrl, book.getPublisher(), book.getAuthor(), book.getCategoryId());
            bookService.save(addBook);
        }
        return new ResponseResult(200, "添加成功");
    }

    @DeleteMapping("/delete")
    public ResponseResult deleteBooks(@RequestBody List<Long> isbns) {
        for (Long isbn : isbns) {
            QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
            bookQueryWrapper.eq("isbn", isbn);
            bookService.remove(bookQueryWrapper);
        }
        return new ResponseResult(200, "删除成功");
    }
}
