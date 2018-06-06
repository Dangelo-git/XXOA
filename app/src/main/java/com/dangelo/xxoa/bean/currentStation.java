package com.dangelo.xxoa.bean;

import java.io.Serializable;

/**
 * Created by dangelo on 18/5/7.
 */

public class currentStation implements Serializable {
    private String id;
    private String userName;
    private String passWord;
    private String userSex;
    private String status;
    private String userPhone;
    private String userAddress;
    private String userCreateTime;
    private String userUpdateTime;
    private String deptId;
    private String deptName;
    private String operatorUserId;
    private String userRoomNumber;
    private String auditStatus;
    private String userEnglishName;
    private String userKey;
    private String userIdNumber;
    private String userDateBirth;
    private String personStatus;

    @Override
    public String toString() {
        return "currentStation{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", userSex='" + userSex + '\'' +
                ", status='" + status + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userCreateTime='" + userCreateTime + '\'' +
                ", userUpdateTime='" + userUpdateTime + '\'' +
                ", deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                ", operatorUserId='" + operatorUserId + '\'' +
                ", userRoomNumber='" + userRoomNumber + '\'' +
                ", auditStatus='" + auditStatus + '\'' +
                ", userEnglishName='" + userEnglishName + '\'' +
                ", userKey='" + userKey + '\'' +
                ", userIdNumber='" + userIdNumber + '\'' +
                ", userDateBirth='" + userDateBirth + '\'' +
                ", personStatus='" + personStatus + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(String userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public String getUserUpdateTime() {
        return userUpdateTime;
    }

    public void setUserUpdateTime(String userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getOperatorUserId() {
        return operatorUserId;
    }

    public void setOperatorUserId(String operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    public String getUserRoomNumber() {
        return userRoomNumber;
    }

    public void setUserRoomNumber(String userRoomNumber) {
        this.userRoomNumber = userRoomNumber;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getUserEnglishName() {
        return userEnglishName;
    }

    public void setUserEnglishName(String userEnglishName) {
        this.userEnglishName = userEnglishName;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getUserIdNumber() {
        return userIdNumber;
    }

    public void setUserIdNumber(String userIdNumber) {
        this.userIdNumber = userIdNumber;
    }

    public String getUserDateBirth() {
        return userDateBirth;
    }

    public void setUserDateBirth(String userDateBirth) {
        this.userDateBirth = userDateBirth;
    }

    public String getPersonStatus() {
        return personStatus;
    }

    public void setPersonStatus(String personStatus) {
        this.personStatus = personStatus;
    }
}
