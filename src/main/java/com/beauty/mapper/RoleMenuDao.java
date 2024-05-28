package com.beauty.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.beauty.entity.RoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色菜单表
 */
@Mapper
public interface RoleMenuDao extends BaseMapper<RoleMenuEntity> {
}