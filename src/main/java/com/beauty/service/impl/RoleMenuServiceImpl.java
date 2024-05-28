package com.beauty.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.beauty.entity.RoleMenuEntity;
import com.beauty.mapper.RoleMenuDao;
import com.beauty.service.RoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色菜单表
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenuEntity> implements RoleMenuService{
}