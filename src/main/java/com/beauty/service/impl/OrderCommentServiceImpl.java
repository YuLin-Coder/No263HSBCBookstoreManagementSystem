package com.beauty.service.impl;

import com.beauty.mapper.OrderCommentDao;
import com.beauty.model.OrderCommentEntity;
import com.beauty.service.OrderCommentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单评价表
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderCommentServiceImpl extends ServiceImpl<OrderCommentDao,OrderCommentEntity> implements OrderCommentService{
}