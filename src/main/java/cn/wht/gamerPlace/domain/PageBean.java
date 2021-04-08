package cn.wht.gamerPlace.domain;

import java.util.List;

/**
 * 分页对象
 * @param <T>
 */
public class PageBean<T>{
    private int totalCount;//总记录数
    private int totalPage;//总页数
    private int currentPage;//当前页面
    private int pageSize;//每页显示数
    private List<T> list;//每页展示数据的集合

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
