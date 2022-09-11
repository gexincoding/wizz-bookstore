package com.wizz.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wizz.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
