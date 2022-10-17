package com.wizz.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wizz.dao.User;
import com.wizz.dto.UserInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
    void updateUser(@Param("username") String username, @Param("password") String encode);

    UserInfoDto getDetails(@Param("user_id") Long userId);
}
