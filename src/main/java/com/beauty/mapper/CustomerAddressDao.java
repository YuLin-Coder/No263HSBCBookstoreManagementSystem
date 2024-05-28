package com.beauty.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.beauty.model.CustomerAddressEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户收货地址
 */
@Mapper
public interface CustomerAddressDao extends BaseMapper<CustomerAddressEntity> {
}