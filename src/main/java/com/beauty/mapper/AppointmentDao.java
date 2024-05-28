package com.beauty.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.beauty.model.AppointmentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 公告
 */
@Mapper
public interface AppointmentDao extends BaseMapper<AppointmentEntity> {
}