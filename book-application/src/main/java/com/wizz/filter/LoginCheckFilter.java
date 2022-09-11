package com.wizz.filter;

import com.alibaba.fastjson.JSON;
import com.wizz.common.BaseContext;
import com.wizz.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String reqUri = request.getRequestURI();
        log.info("拦截到请求:{}", reqUri);

        //设定放行路径
        String[] uris = new String[]{
                "/user/login",
                "/user/logout",
        };

        boolean check = checkUrl(reqUri, uris);

        //如果请求路径为放行路径，则直接放行
        if(check){
            log.info("请求{}已放行", reqUri);
            filterChain.doFilter(request, response);
            return;
        }

        //如果用户已登录，则直接放行
        if(request.getSession().getAttribute("employee")!=null){
            log.info("用户已登录，请求{}已放行", reqUri);

            //这里为线程备份一份用户id
            Long empId = (Long)request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);

            filterChain.doFilter(request,response);
            return;
        }
        if(request.getSession().getAttribute("user")!=null){
            log.info("用户已登录，请求{}已放行", reqUri);

            //这里为线程备份一份用户id
            Long empId = (Long)request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);

            filterChain.doFilter(request,response);
            return;
        }

        //如果不能放行，则跳转到登录页面
        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(ResponseResult.error("NOTLOGIN")));
    }

    public boolean checkUrl(String uri1, String[] uris){
        for (String uri : uris) {
            if(PATH_MATCHER.match(uri,uri1)){
                return true;
            }
        }
        return false;
    }
}
