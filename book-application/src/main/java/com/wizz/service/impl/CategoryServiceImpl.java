package com.wizz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wizz.dao.Category;
import com.wizz.mapper.CategoryMapper;
import com.wizz.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xialinrui
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public void delete(List<Long> ids) {
        categoryMapper.deleteBatchIds(ids);
    }
}
