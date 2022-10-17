package com.wizz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wizz.dao.Propose;
import com.wizz.mapper.ProposeMapper;
import com.wizz.service.ProposeService;
import org.springframework.stereotype.Service;

@Service
public class ProposeServiceImpl extends ServiceImpl<ProposeMapper, Propose> implements ProposeService {
}
