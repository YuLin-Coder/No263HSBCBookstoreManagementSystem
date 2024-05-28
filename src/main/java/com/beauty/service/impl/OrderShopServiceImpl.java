package com.beauty.service.impl;

import com.beauty.mapper.OrderShopDao;
import com.beauty.model.OrderShopEntity;
import com.beauty.service.OrderShopService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单购物详情
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderShopServiceImpl extends ServiceImpl<OrderShopDao,OrderShopEntity> implements OrderShopService{
}