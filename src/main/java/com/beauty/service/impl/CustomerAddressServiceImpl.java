package com.beauty.service.impl;

import com.beauty.mapper.CustomerAddressDao;
import com.beauty.model.CustomerAddressEntity;
import com.beauty.service.CustomerAddressService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 客户收货地址
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerAddressServiceImpl extends ServiceImpl<CustomerAddressDao,CustomerAddressEntity> implements CustomerAddressService{
}