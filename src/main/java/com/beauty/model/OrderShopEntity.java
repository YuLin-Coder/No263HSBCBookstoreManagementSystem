package com.beauty.model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.beauty.entity.BaseEntity;
import lombok.Data;
import com.beauty.table.OrderShopTable;
import java.math.BigDecimal;


/**
 * 订单购物详情
 */
@Data
@TableName("order_shop")
public class OrderShopEntity extends BaseEntity {
  /**
   * 
   */
  @TableField(OrderShopTable.CUSTOMER_ID)
  private String customerId;
  /**
   * 
   */
  @TableField(OrderShopTable.PRICE)
  private BigDecimal price;
  /**
   * 数量
   */
  @TableField(OrderShopTable.NUM)
  private Integer num;
  /**
   * 
   */
  @TableField(OrderShopTable.ORDER_ID)
  private String orderId;
  /**
   * 
   */
  @TableField(OrderShopTable.NAME)
  private String name;
  /**
   * 
   */
  @TableField(OrderShopTable.COVER)
  private String cover;

  private String shopId;

  /**
   * 评价内容
   */
  private String content;
  /**
   *
   */
  private Integer score;


  @TableField(exist = false)
  private String cname;

  @TableField(exist = false)
  private String header;
}
