package com.beauty.entity;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import com.beauty.table.RoleMenuTable;


/**
 * 角色菜单表
 */
@Data
@TableName("role_menu")
public class RoleMenuEntity extends BaseEntity{
  /**
   * 角色ID
   */
  @TableField(RoleMenuTable.ROLE_ID)
  private String roleId;
  /**
   * 菜单ID
   */
  @TableField(RoleMenuTable.MENU_ID)
  private String menuId;
}
