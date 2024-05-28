package com.beauty.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.beauty.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色表
 */
@Mapper
public interface RoleDao extends BaseMapper<RoleEntity> {
}