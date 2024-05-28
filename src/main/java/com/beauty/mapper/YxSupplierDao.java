package com.beauty.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.beauty.model.YxSupplierEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface YxSupplierDao extends BaseMapper<YxSupplierEntity> {

}