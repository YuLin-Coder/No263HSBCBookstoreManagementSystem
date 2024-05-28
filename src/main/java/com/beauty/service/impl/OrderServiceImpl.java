package com.beauty.service.impl;

import com.beauty.mapper.OrderDao;
import com.beauty.model.OrderEntity;
import com.beauty.service.OrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单表
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl extends ServiceImpl<OrderDao,OrderEntity> implements OrderService{
}