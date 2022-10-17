package com.wizz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wizz.dao.Recommend;
import com.wizz.mapper.RecommendMapper;
import com.wizz.service.RecommendService;
import org.springframework.stereotype.Service;

/**
 * @author xialinrui
 */
@Service
public class RecommendServiceImpl extends ServiceImpl<RecommendMapper, Recommend> implements RecommendService {
}
