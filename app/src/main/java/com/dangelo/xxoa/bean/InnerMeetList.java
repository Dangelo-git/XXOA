package com.dangelo.xxoa.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dangelo on 18/5/7.
 */

public class InnerMeetList implements Serializable {
    private String total;
    private String currentpage;
    private String pageTotal;
    private List<InnerMeet> rows;

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

    public List<InnerMeet> getRows() {
        return rows;
    }

    public void setRows(List<InnerMeet> rows) {
        this.rows = rows;
    }
}
