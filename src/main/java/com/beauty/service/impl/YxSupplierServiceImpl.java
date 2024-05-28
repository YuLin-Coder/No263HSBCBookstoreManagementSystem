package com.beauty.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.beauty.mapper.YxSupplierDao;
import com.beauty.model.YxSupplierEntity;
import com.beauty.service.YxSupplierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 评论表
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class YxSupplierServiceImpl extends ServiceImpl<YxSupplierDao, YxSupplierEntity> implements YxSupplierService {
}