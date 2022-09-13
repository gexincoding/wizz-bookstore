package com.wizz.controller;

import com.wizz.entity.LoginUser;
import com.wizz.entity.ResponseResult;
import com.wizz.entity.User;
import com.wizz.service.BookService;
import com.wizz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private BookService bookService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult logout() {
        return userService.logout();
    }


    @GetMapping("/borrow")
    public ResponseResult getBooksByContent() {
      /*  LoginUser currentUserDetails = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = currentUserDetails.getUsername();
        //如果状态为已借空，返回借阅失败信息
        if (bookService.getBookStatusIdByBookName(bookName) == 3){
            return new ResponseResult<>(401,"已经全被借走啦～过几天再来看看哦！");
        }else{//如果状态为有存余：如果库存为1，库存减1，状态该为已借空；如果库存>1，库存-1。绑定借阅者与图书信息。返回成功信息


        }


*/
        return new ResponseResult(200,"已经成功借阅哦～记得及时归还～");

    }

    @RequestMapping("/return/list")
    public ResponseResult getToReturnList() {
        LoginUser currentUserDetails = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = currentUserDetails.getUsername();
        return null;
    }

}
