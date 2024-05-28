package com.beauty.service.impl;

import com.beauty.mapper.CustomerDao;
import com.beauty.model.CustomerEntity;
import com.beauty.service.CustomerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 客户表
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerServiceImpl extends ServiceImpl<CustomerDao,CustomerEntity> implements CustomerService{
}