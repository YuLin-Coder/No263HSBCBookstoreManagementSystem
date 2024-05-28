package com.beauty.config;

/**
 * 返回结果状态枚举
 * @Description 返回结果状态枚举
 **/
public enum ResultCodeEnum {
    SUCCESS(200,"操作成功"),
    ERROR(500,"操作失败"),
    ;
    /**
     * 状态值
     */
    private Integer status;

    /**
     * 状态描述
     */
    private String desc;

    ResultCodeEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    /**
     * 获取状态编码
     * @return
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 获取结果描述
     * @return
     */
    public String getDesc() {
        return desc;
    }
}
