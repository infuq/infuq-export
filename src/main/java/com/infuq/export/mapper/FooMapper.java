package com.infuq.export.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.infuq.export.model.Foo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface FooMapper extends BaseMapper<Foo> {


}
