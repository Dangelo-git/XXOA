package com.dangelo.xxoa.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dangelo on 18/5/9.
 */

public class knowledgeUploadFiles implements Serializable {
    private List<knowledgePersonal> rows;
    private String total;
    private String currentpage;
    private String pageTotal;

    public List<knowledgePersonal> getRows() {
        return rows;
    }

    public void setRows(List<knowledgePersonal> rows) {
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
