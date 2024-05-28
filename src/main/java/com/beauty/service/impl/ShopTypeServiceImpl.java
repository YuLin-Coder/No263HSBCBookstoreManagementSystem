package com.beauty.service.impl;

import com.beauty.mapper.ShopTypeDao;
import com.beauty.model.ShopTypeEntity;
import com.beauty.service.ShopTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品类型
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeDao,ShopTypeEntity> implements ShopTypeService{
}