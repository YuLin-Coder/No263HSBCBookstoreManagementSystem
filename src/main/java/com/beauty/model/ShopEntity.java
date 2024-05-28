package com.beauty.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.beauty.entity.BaseEntity;
import com.beauty.table.ShopTable;
import lombok.Data;

import java.math.BigDecimal;


/**
 * 商品表
 */
@Data
@TableName("shop")
public class ShopEntity extends BaseEntity{
  @Override
  public String toString() {
    return "ShopEntity{" +
            "name='" + name + '\'' +
            ", summery='" + summery + '\'' +
            ", content='" + content + '\'' +
            ", typeId='" + typeId + '\'' +
            ", cover='" + cover + '\'' +
            ", price=" + price +
            ", discountPrice=" + discountPrice +
            ", score=" + score +
            ", stock=" + stock +
            ", status=" + status +
            ", hot=" + hot +
            ", label='" + label + '\'' +
            ", royalty=" + royalty +
            '}';
  }

  /**
   * 商品名称
   */
  @TableField(ShopTable.NAME)
  private String name;
  /**
   * 简单描述
   */
  @TableField(ShopTable.SUMMERY)
  private String summery;
  /**
   * 内容
   */
  @TableField(ShopTable.CONTENT)
  private String content;
  /**
   * 类型ID
   */
  @TableField(ShopTable.TYPE_ID)
  private String typeId;
  /**
   * 封面
   */
  @TableField(ShopTable.COVER)
  private String cover;
  /**
   * 
   */
  @TableField(ShopTable.PRICE)
  private BigDecimal price;
  /**
   * 折扣价格
   */
  @TableField(ShopTable.DISCOUNT_PRICE)
  private BigDecimal discountPrice;
  /**
   * 评分
   */
  @TableField(ShopTable.SCORE)
  private Double score;
  /**
   * 库存剩余
   */
  @TableField(ShopTable.STOCK)
  private Integer stock;

  private Boolean status;

  private boolean hot;

  private String label;

  private BigDecimal royalty;

  @TableField(ShopTable.SUPPERID)
  private String supperId;

  @TableField(ShopTable.ISINVENTORY)
  private Integer isInventory;

  /**
   * 是否删除
   */
  @TableField(ShopTable.ISDEL)
  private Integer isDel;

  /** 供应商名称 */
  @TableField(exist = false)
  private String supperName;
}
