package com.beauty.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.beauty.model.ShoppingGatEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 购物车
 */
@Mapper
public interface ShoppingGatDao extends BaseMapper<ShoppingGatEntity> {
}