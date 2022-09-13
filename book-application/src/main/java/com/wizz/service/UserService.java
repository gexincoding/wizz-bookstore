package com.wizz.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wizz.dto.BookReturnDto;
import com.wizz.entity.ResponseResult;
import com.wizz.entity.User;
import com.wizz.vo.ReturnBookVo;

public interface UserService {

    ResponseResult login(User user);

    ResponseResult logout();


    void borrowBookByBookName(String bookName,String username);

    ResponseResult<Page<BookReturnDto>> getToReturnBooksByReturnBookVo(ReturnBookVo returnBookVo);
}
