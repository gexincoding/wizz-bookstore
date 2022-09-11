package com.wizz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wizz.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService extends IService<User> {

    public UserDetails loadUserByUsername(String username);
}
