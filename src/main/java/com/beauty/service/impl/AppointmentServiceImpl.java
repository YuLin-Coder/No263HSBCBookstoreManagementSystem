package com.beauty.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.beauty.mapper.AppointmentDao;
import com.beauty.model.AppointmentEntity;
import com.beauty.service.AppointmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 公告
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AppointmentServiceImpl extends ServiceImpl<AppointmentDao, AppointmentEntity> implements AppointmentService {
}