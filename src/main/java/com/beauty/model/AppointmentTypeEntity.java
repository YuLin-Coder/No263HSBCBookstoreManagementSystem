package com.beauty.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.beauty.entity.BaseEntity;
import com.beauty.table.AppointmentTypeTable;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


/**
 * 公告分类
 */
@Data
@TableName("appointment_type")
public class AppointmentTypeEntity extends BaseEntity {

    /**
     * 公告分类
     */
    @TableField(AppointmentTypeTable.TYPENAME)
    private String typeName;

    /**
     * 创建时间
     */
    @TableField(AppointmentTypeTable.CREATEDTIME)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;
    /**
     * 是否删除
     */
    @TableField(AppointmentTypeTable.ISDEL)
    private Integer isDel;

}
