package com.wizz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wizz.dao.Advice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdviceMapper extends BaseMapper<Advice> {
}
