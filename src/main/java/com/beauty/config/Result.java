package com.beauty.config;

/**
 * 接口统一返回
 */
public final class Result {
    private int code;
    private Object data;
    private String msg;

    /**
     * 私有无参构造 不允许外部私自新加返回状态
     */
    private Result() {
    }

    /**
     * 带参数构造
     *
     * @param resultCodeEnum 返回结果枚举
     */
    public Result(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getStatus();
        this.msg = resultCodeEnum.getDesc();
    }

    /**
     * 带参数构造
     *
     * @param resultCodeEnum 返回结果编码
     * @param msg            结果描述
     */
    public Result(ResultCodeEnum resultCodeEnum, String msg, Object data) {
        this.code = resultCodeEnum.getStatus();
        this.msg = msg;
        if (null == msg || "".equals(msg)) {
            this.msg = resultCodeEnum.getDesc();
        }
        this.data = data;
    }

    /**
     * 带参数构造
     *
     * @param code 返回结果编码
     * @param msg  返回结果描述
     * @param data 结果描述
     */
    public Result(Integer code, Object data, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 无数据异常返回（code=500）
     *
     * @param msg 结果描述
     * @return
     */
    public static Result error(String msg) {
        return new Result(ResultCodeEnum.ERROR, msg, null);
    }

    public static Result error(Object data, String msg) {
        return new Result(ResultCodeEnum.ERROR, msg, data);
    }


    /**
     * 默认返回结果
     *
     * @return
     */
    public static Result success(Object data) {
        return new Result(ResultCodeEnum.SUCCESS, null, data);
    }

    /**
     * 有数据成功返回（code=200）
     *
     * @param data 返回数据
     * @param msg  结果描述
     * @return
     */
    public static Result success(Object data, String msg) {
        return new Result(ResultCodeEnum.SUCCESS, msg, data);
    }

    /**
     * 有数据成功返回
     *
     * @param code 返回码
     * @param data 返回数据
     * @param msg  结果描述
     * @return
     */
    public static Result success(Integer code, Object data, String msg) {
        return new Result(ResultCodeEnum.SUCCESS, msg, data);
    }

    /**
     * 无数据成功返回（code=200）
     *
     * @param msg 结果描述
     * @return
     */
    public static Result success(String msg) {
        return new Result(ResultCodeEnum.SUCCESS, msg, null);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
