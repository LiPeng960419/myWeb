package com.lipeng.common.mybatis;

import java.util.List;

public class PageParam<T> {

    protected static final Integer DEFAULT_PAGE_SIZE = 10;

    private Integer start;

    private Integer totalCount;

    private Integer totalPage = 1;

    private Integer pageSize = DEFAULT_PAGE_SIZE;

    private Integer curPage = 1;

    private List<T> data;

    private boolean isCut = true;

    public PageParam() {
    }

    public PageParam(int pageSize, int currentPage) {
        this.curPage = Math.max(currentPage, 1);
        this.pageSize = pageSize > 0 ? pageSize : PageParam.DEFAULT_PAGE_SIZE;
        this.start = this.pageSize * (this.curPage - 1);
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public List<T> getData() {
        return data;
    }

    public boolean getIsCut() {
        return isCut;
    }

    public Integer getCurPage() {
        return curPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        if (totalCount == null) {
            return;
        }
        if (this.isCut) {
            this.totalPage = totalCount % this.pageSize == 0 ? totalCount / this.pageSize : totalCount / this.pageSize + 1;
        } else {
            this.totalPage = 1;
            this.pageSize = this.totalCount;
        }
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void setIsCut(boolean isCut) {
        this.isCut = isCut;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage >= 1 ? curPage : 1;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize > 0 ? pageSize : PageParam.DEFAULT_PAGE_SIZE;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

}