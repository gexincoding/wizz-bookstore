package com.wizz.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wizz.dao.Recommend;
import com.wizz.dto.RecommendDto;
import com.wizz.mapper.RecommendMapper;
import com.wizz.service.RecommendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xialinrui
 */
@Service
public class RecommendServiceImpl extends ServiceImpl<RecommendMapper, Recommend> implements RecommendService {
    @Resource
    private RecommendMapper recommendMapper;

    @Override
    public void delete(List<Long> ids) {
        recommendMapper.deleteBatchIds(ids);
    }

    @Override
    public Page<RecommendDto> listPage(Page<RecommendDto> page) {
        return recommendMapper.listPage(page);
    }
}
