package com.beauty.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.beauty.model.AppointmentTypeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 公告分类
 */
@Mapper
public interface AppointmentTypeDao extends BaseMapper<AppointmentTypeEntity> {
}