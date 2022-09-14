package com.wizz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wizz.dto.BookDto;
import com.wizz.dto.BookStatusDto;
import com.wizz.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BookMapper extends BaseMapper<Book> {
    IPage<BookDto> getBookByCategoryName(@Param("page") Page<Book> page, @Param("category_name") String categoryName);

    IPage<BookDto> getBookByContent(@Param("page") Page<Book> page, @Param("content") String content);

    BookStatusDto getBookStatusListByBookName(@Param("book_name") String bookName);

    BookStatusDto getBookStatusListByISBN(@Param("isbn") String ISBN);

    void updateStatusNameByBookName(@Param("book_name") String bookName);

    void updateBookNumberStatusByBookName(@Param("book_name") String bookName);

    //通过书名、出版社名称、类名加入图书
    void addBook(@Param("book_name") String bookName, @Param("publisher") String publisher, @Param("category_name") String categoryName);

    //根据用户名、书名查找到对应的用户id和书id之后，和推荐原因一起存入recommend表
    void setRecommendation(@Param("username") String username, @Param("book_name") String bookName, @Param("reasons") String reasons);

    void insertBook(@Param("book_name") String bookName, @Param("book_ISBN") String bookISBN, @Param("author") String author, @Param("publisher") String publisher);

    void updateBookNumbersByISBN(@Param("book_ISBN") String bookISBN);
}
