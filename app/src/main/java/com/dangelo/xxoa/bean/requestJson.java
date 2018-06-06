package com.dangelo.xxoa.bean;

import com.dangelo.xxoa.uitl.ShortCut;

import java.io.Serializable;

/**
 * Created by dangelo on 18/5/19.
 */

public class requestJson implements Serializable {
    private int  page;
    private int  rows = ShortCut.Page;
    private Long  id ;
    private String   signbatchtypeid  ;
    private String  stationId ;

    public String getSignbatchtypeid() {
        return signbatchtypeid;
    }

    public void setSignbatchtypeid(String signbatchtypeid) {
        this.signbatchtypeid = signbatchtypeid;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
