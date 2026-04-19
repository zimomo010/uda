package com.bu.admin.repository;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class IfPage<T> {
    private List<T> dataList = new ArrayList<>();
    private int totalRecordCount = 0;
    private int pageSize = 10;
    private int pageNum = 1;


    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        if (this.pageSize <= 0) {
            this.pageSize = 10;
        }
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
        if (this.pageNum <= 0) {
            this.pageNum = 1;
        }

    }

    public int getPageCount() {
        return (this.totalRecordCount - 1) / this.pageSize + 1;
    }

    public int getCurrentPageSize() {
        return this.dataList == null ? 0 : this.dataList.size();
    }

    public void setTotalRecordCount(int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
        if (this.totalRecordCount < 0) {
            this.totalRecordCount = 0;
        }
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public int getNextPageNum() {
        return this.pageNum >= this.getPageCount() ? 0 : this.pageNum + 1;
    }

    public int getLastPageNum() {
        return this.pageNum >= this.getPageCount() ? 0 : this.getPageCount();
    }

    public int getFirstPageNum() {
        return this.pageNum <= 1 ? 0 : 1;
    }

    public int getPrePageNum() {
        return this.pageNum - 1;
    }

}
