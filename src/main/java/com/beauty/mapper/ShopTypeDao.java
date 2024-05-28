package com.beauty.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.beauty.model.ShopTypeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品类型
 */
@Mapper
public interface ShopTypeDao extends BaseMapper<ShopTypeEntity> {
}