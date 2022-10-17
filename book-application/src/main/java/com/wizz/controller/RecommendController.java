package com.wizz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wizz.dao.LoginUser;
import com.wizz.dao.Recommend;
import com.wizz.dao.ResponseResult;
import com.wizz.dao.User;
import com.wizz.dto.RecommendDto;
import com.wizz.service.RecommendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xialinrui
 */
@RestController
@Slf4j
@RequestMapping("/recommend")
public class RecommendController {
    @Resource
    private RecommendService recommendService;


    @PostMapping("/add")
    public ResponseResult add(@RequestBody Recommend recommend) {
        LoginUser currentUserDetails = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = currentUserDetails.getUser();
        recommend.setUserId(user.getUserId());
        recommendService.save(recommend);
        return new ResponseResult(200, "添加成功");
    }

    @DeleteMapping("/delete")
    public ResponseResult delete(@RequestBody List<Long> ids) {
        recommendService.delete(ids);
        return new ResponseResult(200, "删除成功");
    }

    @GetMapping("/list")
    public ResponseResult<Page<RecommendDto>> list(@RequestParam Integer page, @RequestParam Integer pageSize) {
        Page<RecommendDto> resPage = new Page<>(page, pageSize);
        resPage = recommendService.listPage(resPage);
        return new ResponseResult<>(200, "查询成功", resPage);
    }

}