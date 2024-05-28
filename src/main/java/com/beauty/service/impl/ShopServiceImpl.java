package com.beauty.service.impl;

import com.beauty.mapper.ShopDao;
import com.beauty.model.ShopEntity;
import com.beauty.service.ShopService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品表
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShopServiceImpl extends ServiceImpl<ShopDao,ShopEntity> implements ShopService{
}