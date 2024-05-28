package com.beauty.config;


import java.util.List;

/**
 * 分页数据
 */
public final class PageVo<T> {
    /**
     * 数据
     */
    private List<T> data;

    /**
     * 当前页码
     */
    private int pageSize = 1;

    /**
     * 查询多少条数据
     */
    private int pageNum = 10;

    /**
     * 总数
     */
    private long count;

    /**
     * 0
     */
    private int code;

    /**
     * msg
     */
    private String msg;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
