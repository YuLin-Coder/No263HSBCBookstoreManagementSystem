package com.beauty.model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.beauty.entity.BaseEntity;
import lombok.Data;
import com.beauty.table.CustomerTable;


/**
 * 客户表
 */
@Data
@TableName("customer")
public class CustomerEntity extends BaseEntity{
  /**
   * 名称
   */
  @TableField(CustomerTable.NAME)
  private String name;
  /**
   * password
   */
  @TableField(CustomerTable.PHONE)
  private String phone;
  /**
   * 密码
   */
  @TableField(CustomerTable.PASSWORD)
  private String password;
  /**
   * 头像
   */
  @TableField(CustomerTable.HEADER)
  private String header;
  /**
   * 1 普通客户  2认证客户
   */
  @TableField(CustomerTable.TYPE)
  private Integer type;
  /**
   * 认证标签
   */
  @TableField(CustomerTable.AUTH_NAME)
  private String authName;
  /**
   * 认证申请状态  0 未申请  1申请中  2已通过  3已拒绝
   */
  @TableField(CustomerTable.AUTH_STATUS)
  private Integer authStatus;
  /**
   * 认证描述
   */
  @TableField(CustomerTable.AUTH_MARK)
  private String authMark;
  /**
   * 积分信息
   */
  @TableField(CustomerTable.INTEGRAL)
  private Integer integral;
}
