package com.jeanboy.app.mvpdemo.common.utils;

/**
 * Created by Next on 2016/7/5.
 */
public class Page {

    private final static int DEFAULT_SIZE = 10;

    private int current;//当前页码
    private int size = DEFAULT_SIZE;//每页显示条数，默认10条
    private int total;//总条数

    public Page(int current, int total) {
        this(current, DEFAULT_SIZE, total);
    }

    public Page(int current, int size, int total) {
        this.current = current;
        this.size = size;
        this.total = total;
    }

    /**
     * 设置当前页码
     *
     * @param current
     */
    public void setCurrent(int current) {
        if (current < 1) {
            this.current = 1;
        } else if (current >= total) {
            this.current = total;
        } else {
            this.current = current;
        }
    }

    /**
     * 设置每页显示条数
     *
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 设置总条数
     *
     * @param total
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * 获取总页数
     *
     * @return
     */
    public int getPageCount() {
        return total % size == 0 ? total / size : (total / size + 1);
    }

    /**
     * 是否有下一页
     *
     * @return
     */
    public boolean hasNext() {
        return getPageCount() > current;
    }

    /**
     * 是否有上一页
     *
     * @return
     */
    public boolean hasPrevious() {
        return current > 1;
    }
}
