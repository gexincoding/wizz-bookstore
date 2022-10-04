package com.wizz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wizz.dao.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {



}
