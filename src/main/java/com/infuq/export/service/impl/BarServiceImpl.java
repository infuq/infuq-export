package com.infuq.export.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infuq.export.mapper.BarMapper;
import com.infuq.export.model.Bar;
import com.infuq.export.service.IBarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class BarServiceImpl extends ServiceImpl<BarMapper, Bar> implements IBarService {

    @Resource
    private BarMapper barMapper;



}
