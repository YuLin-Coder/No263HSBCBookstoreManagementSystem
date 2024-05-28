package com.beauty.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.beauty.model.ShopEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品表
 */
@Mapper
public interface ShopDao extends BaseMapper<ShopEntity> {
}