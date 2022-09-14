package com.wizz.filter;

import com.wizz.entity.LoginUser;
import com.wizz.utils.JwtUtil;
import com.wizz.utils.RedisCache;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    /**
     * 获取token（只有userId）
     * 解析token
     * 从redis中获取user信息
     * 用于认证，必须要把认证信息存入SecurityContentHolder中
     * 后续的过滤器的放行会从中获取信息
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //前端会携带token
        //获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //请求头没有携带token
            //直接放行
            //因为如果登陆，会进行验证
            //否则后续的过滤器会拦截
            filterChain.doFilter(request, response);

            //为了防止放行代码执行完毕再次执行后续代码，必须返回
            return;
        }
        //如果请求头中携带了token
        //解析token
        String userId;
        try {
            //利用Jwt工具包解析token
            Claims claims = JwtUtil.parseJWT(token);
            //获取一个userId
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        //从redis中获取用户信息
        //获取redis的key
        String redisKey = "login:" + userId;
        //获取LoginUser对象
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        //如果token获取不到id（有可能是因为被篡改等）
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("用户未登录");
        }
        //存入SecurityContextHolder
        //用于认证，必须要把认证信息存入SecurityContentHolder中
        //后续的过滤器的放行会从中获取信息
        //获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        log.info("请求通过");
        //放行
        filterChain.doFilter(request, response);
    }
}
