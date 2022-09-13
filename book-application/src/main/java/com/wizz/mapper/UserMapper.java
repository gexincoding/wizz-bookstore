package com.wizz.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wizz.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

    void addNewBorrowRecordByBookNameAndUserName(@Param("book_name") String bookName, @Param("username")String username);

}
