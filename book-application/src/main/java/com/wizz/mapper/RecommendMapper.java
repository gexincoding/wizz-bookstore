package com.wizz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wizz.dao.Recommend;
import com.wizz.dto.RecommendDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author xialinrui
 */
@Mapper
@Repository
public interface RecommendMapper extends BaseMapper<Recommend> {
    Page<RecommendDto> listPage(Page<RecommendDto> page);
}
