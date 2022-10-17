package com.wizz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wizz.dao.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {
    void delete(List<Long> ids);
}
