package com.wizz.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wizz.dto.BookDto;
import com.wizz.dto.BookReturnDto;
import com.wizz.entity.Book;
import com.wizz.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

    void addNewBorrowRecordByBookNameAndUserName(@Param("book_name") String bookName, @Param("username")String username);

    IPage<BookReturnDto> getToReturnBookByUsername(@Param("page") Page<BookReturnDto> page, @Param("username") String Username);
}
