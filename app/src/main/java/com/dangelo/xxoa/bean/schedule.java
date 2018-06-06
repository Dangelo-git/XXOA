package com.dangelo.xxoa.bean;

import java.io.Serializable;

/**
 * Created by dangelo on 16/9/28.
 */

/**
 * "schedules":{"id":1539,"topic":"蝴蝶姐姐","address":"急急急","startDate":"2016年12月22日",
 * "endDate":"2016年12月22日","status":"","remark":"","constitutor":"系统管理员",
 * "constitutorId":"1","attends":"","attendsId":"","isAttend":"","spaceNoon":"","isallDay":"true"}
 */
public class schedule implements Serializable {
    private String id;
    private String topic;
    private String address;
    private String startDate;
    private String endDate;//套餐有效期
    private String remark;
    private String isallDay;
    private String constitutor;
    private String constitutorId;
    private String status;


    private String attends;
    private String attendsId;
    private String isAttend;
    private String spaceNoon;

    public String getAttends() {
        return attends;
    }

    public void setAttends(String attends) {
        this.attends = attends;
    }

    public String getAttendsId() {
        return attendsId;
    }

    public void setAttendsId(String attendsId) {
        this.attendsId = attendsId;
    }

    public String getIsAttend() {
        return isAttend;
    }

    public void setIsAttend(String isAttend) {
        this.isAttend = isAttend;
    }

    public String getSpaceNoon() {
        return spaceNoon;
    }

    public void setSpaceNoon(String spaceNoon) {
        this.spaceNoon = spaceNoon;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getIsallDay() {
        return isallDay;
    }

    public void setIsallDay(String isallDay) {
        this.isallDay = isallDay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConstitutorId() {
        return constitutorId;
    }

    public void setConstitutorId(String constitutorId) {
        this.constitutorId = constitutorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public schedule() {

    }



    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }



    public String getConstitutor() {
        return constitutor;
    }

    public void setConstitutor(String constitutor) {
        this.constitutor = constitutor;
    }

    @Override
    public String toString() {
        return "schedule{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", address='" + address + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", remark='" + remark + '\'' +
                ", isallday='" + isallDay + '\'' +
                ", constitutor='" + constitutor + '\'' +
                ", constitutorId='" + constitutorId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
