package com.infuq.export.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.infuq.export.mapper")
public class MybatisPlusConfig {


}
