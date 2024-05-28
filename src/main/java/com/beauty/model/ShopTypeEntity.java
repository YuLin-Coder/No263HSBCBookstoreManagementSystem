package com.beauty.model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.beauty.entity.BaseEntity;
import lombok.Data;
import com.beauty.table.ShopTypeTable;


/**
 * 商品类型
 */
@Data
@TableName("shop_type")
public class ShopTypeEntity extends BaseEntity{
  /**
   * 名称
   */
  @TableField(ShopTypeTable.NAME)
  private String name;
}
