package com.wizz.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wizz.dto.BookDto;
import com.wizz.dto.BookReturnDto;
import com.wizz.entity.ResponseResult;
import com.wizz.entity.User;
import com.wizz.vo.BookVo;

public interface UserService extends IService<User>{

    ResponseResult login(User user);

    ResponseResult logout();

    void borrowBookByBookName(String bookName,String username);

    ResponseResult<Page<BookReturnDto>> getToReturnBooksByReturnBookVo(BookVo bookVo);

    ResponseResult<Page<BookDto>> getFavouritesBooksByBookVo(BookVo bookVo);


    void changePasswordByUsernameAndNewPassword(String username, String encode);


}
