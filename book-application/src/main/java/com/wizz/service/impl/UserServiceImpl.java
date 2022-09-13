package com.wizz.service.impl;

import com.wizz.entity.ResponseResult;
import com.wizz.entity.LoginUser;
import com.wizz.entity.User;
import com.wizz.mapper.UserMapper;
import com.wizz.service.UserService;
import com.wizz.utils.JwtUtil;
import com.wizz.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    //配置类中已经重写,获取到对象，放入容器
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        //AuthenticationManager authenticate进行用户认证
        //要把登陆的时候传进来的用户名密码封装成AuthenticationToken对象
        //实现类是UsernamePasswordAuthenticationToken
        //下名为authenticationToken
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        //会查询数据库，进行比对
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过（authenticate==null），给出对应的提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }
        //如果认证通过了（authenticate!=null）
        //返回的信息被封装成了authenticate
        //authenticate中的principal属性是一个loginUser（UserDetail）对象
        //存储着返回的用户信息（正确的用户信息）
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        //使用userid生成一个jwt
        String userId = loginUser.getUser().getUserId().toString();
        //利用Jwt工具包生成token密钥
        String jwt = JwtUtil.createJWT(userId);
        //jwt存入ResponseResult返回
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        //把完整的用户信息存入redis
        //userid作为key
        redisCache.setCacheObject("login:" + userId, loginUser);
        return new ResponseResult(200, "登录成功", map);
    }

    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder中的用户id
        //不用删除SecurityContextHolder中的值
        //因为每个请求的SecurityContextHolder中的内容不一样
        //当前的请求之所以能获取到SecurityContextHolder中的值
        //是因为之前在JwtAuthenticationTokenFilter这个过滤器中已经做了该用户得token校验
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder
                .getContext()
                .getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getUserId();
        //删除redis中的值
        redisCache.deleteObject("login:" + userid);
        //返回信息
        return new ResponseResult(200, "注销成功");
    }


}
