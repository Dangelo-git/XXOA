package com.dangelo.xxoa.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dangelo on 18/5/7.
 */

public class messageNum implements Serializable {

    /*
     tableConferenceNameNum 内部会议消息数量
     tableMeetingNameNum 外部会议消息数量
     massageSum 消息总量
     table1 档案消息量
     table2 发文数量
     table3 收文数量
     */
    private String tableConferenceNameNum;
    private String tableMeetingNameNum;
    private String massageSum;
    private String table1;
    private String table2;
    private String table3;


    public String getTableConferenceNameNum() {
        return tableConferenceNameNum;
    }

    public void setTableConferenceNameNum(String tableConferenceNameNum) {
        this.tableConferenceNameNum = tableConferenceNameNum;
    }

    public String getTableMeetingNameNum() {
        return tableMeetingNameNum;
    }

    public void setTableMeetingNameNum(String tableMeetingNameNum) {
        this.tableMeetingNameNum = tableMeetingNameNum;
    }

    public String getMassageSum() {
        return massageSum;
    }

    public void setMassageSum(String massageSum) {
        this.massageSum = massageSum;
    }

    public String getTable1() {
        return table1;
    }

    public void setTable1(String table1) {
        this.table1 = table1;
    }

    public String getTable2() {
        return table2;
    }

    public void setTable2(String table2) {
        this.table2 = table2;
    }

    public String getTable3() {
        return table3;
    }

    public void setTable3(String table3) {
        this.table3 = table3;
    }




}
