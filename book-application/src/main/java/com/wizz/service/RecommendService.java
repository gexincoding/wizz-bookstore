package com.wizz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wizz.dao.Recommend;
import com.wizz.dto.RecommendDto;

import java.util.List;

/**
 * @author xialinrui
 */
public interface RecommendService extends IService<Recommend> {
    void delete(List<Long> ids);

    Page<RecommendDto> listPage(Page<RecommendDto> page);
}
