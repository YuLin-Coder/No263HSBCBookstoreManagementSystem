package com.beauty.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.beauty.entity.BaseEntity;
import com.beauty.table.AppointmentTable;
import lombok.Data;

import java.util.Date;


/**
 * 公告表
 */
@Data
@TableName("appointment")
public class AppointmentEntity extends BaseEntity {

    /**
     * 公告分类
     */
    @TableField(AppointmentTable.TYPEID)
    private String typeId;
    /**
     * 发布者ID
     */
    @TableField(AppointmentTable.USERNAME)
    private String userName;
    /**
     * 标题
     */
    @TableField(AppointmentTable.TITLE)
    private String title;
    /**
     * 服务什么内容
     */
    @TableField(AppointmentTable.CONTENT)
    private String content;
    /**
     * 创建时间
     */
    @TableField(AppointmentTable.CREATEDTIME)
    private Date createdTime;
    /**
     * 是否删除
     */
    @TableField(AppointmentTable.ISDEL)
    private Integer isDel;

}
