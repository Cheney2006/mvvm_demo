package com.cheney.mvvm_demo.bean;

import java.util.List;

/**
 * 返回数据
 */
public class Result<T> {

    public static final int STATUS_SUCCESS = 100;
    /**
     * page : 1
     * page_count : 245
     * status : 100
     * data : [] 集合
     * data : {} 单个
     * total_counts : 2445
     */

    private int page;
    private int page_count;
    private int status;
    private int total_counts;
    private T data;

    public boolean isOk() {
        return status == STATUS_SUCCESS;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotal_counts() {
        return total_counts;
    }

    public void setTotal_counts(int total_counts) {
        this.total_counts = total_counts;
    }
}
