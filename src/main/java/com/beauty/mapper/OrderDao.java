package com.beauty.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.beauty.model.OrderEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单表
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
}