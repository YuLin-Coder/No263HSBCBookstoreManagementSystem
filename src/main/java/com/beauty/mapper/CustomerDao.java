package com.beauty.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.beauty.model.CustomerEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户表
 */
@Mapper
public interface CustomerDao extends BaseMapper<CustomerEntity> {
}