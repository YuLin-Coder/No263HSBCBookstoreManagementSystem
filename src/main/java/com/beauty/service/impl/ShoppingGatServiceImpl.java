package com.beauty.service.impl;

import com.beauty.mapper.ShoppingGatDao;
import com.beauty.model.ShoppingGatEntity;
import com.beauty.service.ShoppingGatService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 购物车
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShoppingGatServiceImpl extends ServiceImpl<ShoppingGatDao,ShoppingGatEntity> implements ShoppingGatService{
}