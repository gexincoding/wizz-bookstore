package com.wizz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wizz.dao.Category;
import com.wizz.mapper.CategoryMapper;
import com.wizz.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
