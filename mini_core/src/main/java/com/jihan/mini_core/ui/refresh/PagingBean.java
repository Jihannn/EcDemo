package com.jihan.mini_core.ui.refresh;

/**
 * @author Jihan
 * @date 2019/8/17
 */
public final class PagingBean {

    //当前页数
    private int curPage = 0;
    //总共页数
    private int pageCount = 0;
    //信息总数
    private int total = 0;
    //已显示信息数目
    private int size = 0;
    //是否到底
    private boolean over = false;
    private int delayed = 0;

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getCurPage() {
        return curPage;
    }

    public PagingBean setCurPage(int curPage) {
        this.curPage = curPage;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public PagingBean setTotal(int total) {
        this.total = total;
        return this;
    }

    public int getPageCount() {
        return pageCount;
    }

    public PagingBean setPageCount(int pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    public int getSize() {
        return size;
    }

    public PagingBean setSize(int size) {
        this.size = size;
        return this;
    }

    public int getDelayed() {
        return delayed;
    }

    public PagingBean setDelayed(int delayed) {
        this.delayed = delayed;
        return this;
    }

    public PagingBean addIndex() {
        ++curPage;
        return this;
    }
}
