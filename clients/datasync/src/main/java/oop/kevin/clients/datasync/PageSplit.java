package oop.kevin.clients.datasync;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MichaelLee
 * Date: 12-12-21
 * Time: 下午2:54
 * To change this template use File | Settings | File Templates.
 */
public class PageSplit {
    int page = 1;// 请求显第page页
    int count = 1; // 记录总数
    int currentPage = 1;// 实际显示currentPage页
    int pageCount = 1;// 总页面数
    int rows = 1; // 每页显示的行数
    List list = null;

    /**
     * 构造方法一
     *
     * @param list
     */
    public PageSplit(List list) {
        super();
        this.list = list;
    }

    /**
     * 构造方法二
     *
     * @param rows 每页显示的行数
     * @param list 准备进行分页的List对象
     */
    public PageSplit(int rows, List list) {
        super();
        this.rows = rows;
        this.list = list;
    }

    /**
     * 返回第page页的所有内容，结果仍为List型
     *
     * @param page
     * @return
     */
    public List currentList(int page) {
        this.page = page;
        currentPage = page;
        count = list.size();
        pageCount = (count + rows - 1) / rows; // 根据你的纪录总数，显示行数，来计算分多少页

    /* 如果请求页小于1,则让当前页面数等于1 */
        if (currentPage < 1) {
            currentPage = 1;
        }
    /* 如果请求页大于总页数,则让当前页面数等于总页数 */
        if (currentPage > pageCount) {
            currentPage = pageCount;
        }

        List newList;
    /* 不是满页的情况 */
        if ((((currentPage - 1) * rows) + rows) > count) {
            return newList = list.subList((currentPage - 1) * rows, count);
        }
    /* 恰巧每页都满的情况 */
        else {
            return newList = list.subList((currentPage - 1) * rows,
                    ((currentPage - 1) * rows) + rows);
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
