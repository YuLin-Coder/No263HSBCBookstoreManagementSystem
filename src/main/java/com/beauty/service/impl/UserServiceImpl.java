package com.beauty.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.beauty.entity.UserEntity;
import com.beauty.mapper.UserDao;
import com.beauty.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户表
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService{
}