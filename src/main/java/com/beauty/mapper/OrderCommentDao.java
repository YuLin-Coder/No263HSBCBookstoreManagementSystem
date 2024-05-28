package com.beauty.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.beauty.model.OrderCommentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单评价表
 */
@Mapper
public interface OrderCommentDao extends BaseMapper<OrderCommentEntity> {
}