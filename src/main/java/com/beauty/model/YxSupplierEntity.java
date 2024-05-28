package com.beauty.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.beauty.entity.BaseEntity;
import com.beauty.table.YxSupplierTable;
import lombok.Data;

import java.util.Date;


/**
 * 公告表
 */
@Data
@TableName("yx_supplier")
public class YxSupplierEntity extends BaseEntity {

    /**
     * 供应商名称
     */
    @TableField(YxSupplierTable.NAME)
    private String name;
    /**
     * 供应商地址
     */
    @TableField(YxSupplierTable.ADDRESS)
    private String address;
    /**
     * 供应商电话
     */
    @TableField(YxSupplierTable.PHONE)
    private String phone;
    /**
     * 供应商负责人
     */
    @TableField(YxSupplierTable.HEADNAME)
    private String headName;
    /**
     * 创建时间
     */
    @TableField(YxSupplierTable.CREATEDTIME)
    private Date createdTime;
    /**
     * 是否删除;0否1是
     */
    @TableField(YxSupplierTable.ISDEL)
    private Integer isDel;

}
