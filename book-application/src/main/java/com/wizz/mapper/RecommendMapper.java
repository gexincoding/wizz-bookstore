package com.wizz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wizz.dao.Recommend;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author xialinrui
 */
@Mapper
@Repository
public interface RecommendMapper extends BaseMapper<Recommend> {
}
