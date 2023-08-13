package com.infuq.export.controller;

import com.infuq.export.service.IBarService;
import com.infuq.export.service.IFooService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("local")
@Slf4j
public class Controller {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedissonClient redissonClient;


    @Resource
    private IFooService fooService;
    @Resource
    private IBarService barService;


    @GetMapping("export")
    public void export() {





    }




}
