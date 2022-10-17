package com.wizz.controller;

import com.wizz.dao.Category;
import com.wizz.dao.ResponseResult;
import com.wizz.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xialinrui
 */
@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @PostMapping("/add")
    public ResponseResult add(@RequestBody Category category) {
        categoryService.save(category);
        return new ResponseResult(200, "添加成功");
    }

    @GetMapping("/list")
    public ResponseResult<List<Category>> list() {
        return new ResponseResult<>(200, "查询成功", categoryService.list());
    }
    @DeleteMapping("/delete")
    public ResponseResult delete(@RequestBody List<Long> ids) {
        categoryService.delete(ids);
        return new ResponseResult(200, "删除成功");

    }

}
