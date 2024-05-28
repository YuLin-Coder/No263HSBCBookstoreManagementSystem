package com.beauty.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.beauty.model.OrderShopEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单购物详情
 */
@Mapper
@Repository
public interface OrderShopDao extends BaseMapper<OrderShopEntity> {

    /**
     * 平均评分
     * @param sid
     * @return
     */
    @Select("SELECT AVG(score)num FROM order_shop WHERE shop_id = '${sid}' and score is not NULL ")
    Double num(@Param("sid")String sid);

    @Select("SELECT order_shop.*,customer.`name` cname,customer.header FROM order_shop LEFT JOIN customer ON order_shop.customer_id = customer.id WHERE shop_id = '${sid}' and  content is not null ")
    List<OrderShopEntity> list(@Param("sid")String sid);
}