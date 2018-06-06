package com.dangelo.xxoa.bean;

import java.io.Serializable;

/**
 * Created by dangelo on 18/5/7.
 */

public class node implements Serializable {
    private String name;
    private String receivetypeid;
    private String presonsationid;
    private String signbatchtypeid;
    private String receivedeptid;
    private String conditionText;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReceivetypeid() {
        return receivetypeid;
    }

    public void setReceivetypeid(String receivetypeid) {
        this.receivetypeid = receivetypeid;
    }

    public String getPresonsationid() {
        return presonsationid;
    }

    public void setPresonsationid(String presonsationid) {
        this.presonsationid = presonsationid;
    }

    public String getSignbatchtypeid() {
        return signbatchtypeid;
    }

    public void setSignbatchtypeid(String signbatchtypeid) {
        this.signbatchtypeid = signbatchtypeid;
    }

    public String getReceivedeptid() {
        return receivedeptid;
    }

    public void setReceivedeptid(String receivedeptid) {
        this.receivedeptid = receivedeptid;
    }

    public String getConditionText() {
        return conditionText;
    }

    public void setConditionText(String conditionText) {
        this.conditionText = conditionText;
    }

    @Override
    public String toString() {
        return "node{" +
                "name='" + name + '\'' +
                ", receivetypeid='" + receivetypeid + '\'' +
                ", presonsationid='" + presonsationid + '\'' +
                ", signbatchtypeid='" + signbatchtypeid + '\'' +
                ", receivedeptid='" + receivedeptid + '\'' +
                ", conditionText='" + conditionText + '\'' +
                '}';
    }
}
