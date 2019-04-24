package com.itheima.domain;

import java.util.List;

/**
 * @author zb
 * @description
 *
 *分页对象
 * @date 2019/4/21
 */
public class PageBean<T> {
    private int totalPage; //总页码
    private int rows; //每页显示记录数
    private int totalCount; //总条数
    private List<T> list; //每页的数据集合
    private int currentPage; //当前页码

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalPage=" + totalPage +
                ", rows=" + rows +
                ", totalCount=" + totalCount +
                ", list=" + list +
                ", currentPage=" + currentPage +
                '}';
    }
}
