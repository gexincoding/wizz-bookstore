package com.wizz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wizz.dao.Book;
import com.wizz.dao.Propose;
import com.wizz.dao.ResponseResult;
import com.wizz.service.ProposeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xialinrui
 */
@RestController
@Slf4j
@RequestMapping("/propose")
public class ProposeController {

    @Resource
    private ProposeService proposeService;

    @PostMapping("/add")
    public ResponseResult add(@RequestBody Propose propose) {
        proposeService.save(propose);
        return new ResponseResult(200, "添加成功");
    }

    @DeleteMapping("/delete")
    public ResponseResult delete(@RequestParam Long id) {
        QueryWrapper<Propose> proposeQueryWrapper = new QueryWrapper<>();
        proposeQueryWrapper.eq("id", id);
        proposeService.remove(proposeQueryWrapper);
        return new ResponseResult(200, "删除成功");
    }


    /**
     * 查看所有建议
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public ResponseResult list(@RequestParam Integer page, @RequestParam Integer pageSize) {
        Page<Propose> resPage = new Page<>(page, pageSize);
        QueryWrapper<Propose> proposeQueryWrapper = new QueryWrapper<>();
        proposeQueryWrapper.orderByDesc("update_time").orderByDesc("create_time").orderByDesc("user_id");
        proposeService.page(resPage, proposeQueryWrapper);
        return new ResponseResult(200, "查询成功", resPage);
    }




}
