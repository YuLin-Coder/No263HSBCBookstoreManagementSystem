package com.beauty.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.beauty.mapper.AppointmentTypeDao;
import com.beauty.model.AppointmentTypeEntity;
import com.beauty.service.AppointmentTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 公告分类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AppointmentTypeServiceImpl extends ServiceImpl<AppointmentTypeDao, AppointmentTypeEntity> implements AppointmentTypeService {
}