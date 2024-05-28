package com.beauty.model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.beauty.entity.BaseEntity;
import lombok.Data;
import com.beauty.table.OrderCommentTable;


/**
 * 订单评价表
 */
@Data
@TableName("order_comment")
public class OrderCommentEntity extends BaseEntity{
  /**
   * 
   */
  @TableField(OrderCommentTable.CUSTOMER_ID)
  private String customerId;
  /**
   * 
   */
  @TableField(OrderCommentTable.ORDER_ID)
  private String orderId;
  /**
   * 
   */
  @TableField(OrderCommentTable.SHOP_ID)
  private String shopId;
  /**
   * 评价内容
   */
  @TableField(OrderCommentTable.CONTENT)
  private String content;
  /**
   * 
   */
  @TableField(OrderCommentTable.SCORE)
  private Integer score;
}
