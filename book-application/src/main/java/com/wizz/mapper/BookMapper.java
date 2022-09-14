package com.wizz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wizz.dto.BookDto;
import com.wizz.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface BookMapper extends BaseMapper<Book> {
    IPage<BookDto> getBookByCategoryId(@Param("page") Page<Book> page, @Param("category_name") String categoryName);

    IPage<BookDto> getBookByContent(@Param("page") Page<Book> page, @Param("content") String content);
}
