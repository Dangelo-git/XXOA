package com.dangelo.xxoa.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dangelo on 16/12/19.
 */
public class noticeList implements Serializable {
    private List<noticeInfo> rows;
    private String total;
    private String currentpage;
    private String pageTotal;

    public List<noticeInfo> getRows() {
        return rows;
    }

    public void setRows(List<noticeInfo> rows) {
        this.rows = rows;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(String currentpage) {
        this.currentpage = currentpage;
    }

    public String getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(String pageTotal) {
        this.pageTotal = pageTotal;
    }
}
