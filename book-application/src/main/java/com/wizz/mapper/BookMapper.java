package com.wizz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wizz.dao.Book;
import com.wizz.dto.UserBorrowInfoDto;
import com.wizz.dto.UserInfoDto;
import com.wizz.vo.BorrowInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xialinrui
 */
@Repository
@Mapper
public interface BookMapper extends BaseMapper<Book> {

    BorrowInfoVo getBorrowInfo(@Param("book_id") Long bookId, @Param("user_id") Long userId);

    void borrow(@Param("book_id") Long bookId, @Param("user_id") Long userId);

    void returnBook(@Param("book_id") Long bookId, @Param("user_id") Long userId);

    List<UserBorrowInfoDto> getBorrower(@Param("book_id") Long bookId);
}
