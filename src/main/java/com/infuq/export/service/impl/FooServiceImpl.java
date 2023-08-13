package com.infuq.export.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infuq.export.mapper.FooMapper;
import com.infuq.export.model.Foo;
import com.infuq.export.service.IFooService;
import org.springframework.stereotype.Service;

@Service
public class FooServiceImpl extends ServiceImpl<FooMapper, Foo> implements IFooService {


}
