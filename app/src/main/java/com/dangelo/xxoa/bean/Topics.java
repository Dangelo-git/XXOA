package com.dangelo.xxoa.bean;

/**
 * Created by dangelo on 16/11/21.
 */
public class Topics{
//{
//    "uuEName": "用户名",
//            "meetID": 会议id,
//        "orderNo": 排序号,
//        "topicName": "议题名称,
//        "attendDept": "列席",
//        "reportDept": "汇报单位",
//        "reportDeptId": "汇报单位id
//}
private String uuEName;
private String meetID;
private String orderNo;
private String topicName;
private String attendDept;
private String reportDept;
private String reportDeptId;

    public String getUuEName() {
        return uuEName;
    }

    public void setUuEName(String uuEName) {
        this.uuEName = uuEName;
    }

    public String getMeetID() {
        return meetID;
    }

    public void setMeetID(String meetID) {
        this.meetID = meetID;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getAttendDept() {
        return attendDept;
    }

    public void setAttendDept(String attendDept) {
        this.attendDept = attendDept;
    }

    public String getReportDept() {
        return reportDept;
    }

    public void setReportDept(String reportDept) {
        this.reportDept = reportDept;
    }

    public String getReportDeptId() {
        return reportDeptId;
    }

    public void setReportDeptId(String reportDeptId) {
        this.reportDeptId = reportDeptId;
    }

    @Override
    public String toString() {
        return "Topics{" +
                "uuEName='" + uuEName + '\'' +
                ", meetID='" + meetID + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", topicName='" + topicName + '\'' +
                ", attendDept='" + attendDept + '\'' +
                ", reportDept='" + reportDept + '\'' +
                ", reportDeptId='" + reportDeptId + '\'' +
                '}';
    }
}
