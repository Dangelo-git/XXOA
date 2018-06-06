package com.dangelo.xxoa.bean;

import java.io.Serializable;

/**
 * Created by dangelo on 18/5/9.
 */

public class ExMeet implements Serializable {
    private String id;
    private String externalMeetingId;
    private String meetingName;
    private String userId;
    private String userName;
    private String signUp;
    private String leaveReason;
    private String meetingType;
    private String meetingTime;
    private String meetingPlace;
    private String externalMeetingStatus;
    private String meetingRequirement;
    private String leaderShip;
    private String approverName;
    private String newInformation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExternalMeetingId() {
        return externalMeetingId;
    }

    public void setExternalMeetingId(String externalMeetingId) {
        this.externalMeetingId = externalMeetingId;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSignUp() {
        return signUp;
    }

    public void setSignUp(String signUp) {
        this.signUp = signUp;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    public String getMeetingPlace() {
        return meetingPlace;
    }

    public void setMeetingPlace(String meetingPlace) {
        this.meetingPlace = meetingPlace;
    }

    public String getExternalMeetingStatus() {
        return externalMeetingStatus;
    }

    public void setExternalMeetingStatus(String externalMeetingStatus) {
        this.externalMeetingStatus = externalMeetingStatus;
    }

    public String getMeetingRequirement() {
        return meetingRequirement;
    }

    public void setMeetingRequirement(String meetingRequirement) {
        this.meetingRequirement = meetingRequirement;
    }

    public String getLeaderShip() {
        return leaderShip;
    }

    public void setLeaderShip(String leaderShip) {
        this.leaderShip = leaderShip;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    public String getNewInformation() {
        return newInformation;
    }

    public void setNewInformation(String newInformation) {
        this.newInformation = newInformation;
    }

    @Override
    public String toString() {
        return "ExMeet{" +
                "id='" + id + '\'' +
                ", externalMeetingId='" + externalMeetingId + '\'' +
                ", meetingName='" + meetingName + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", signUp='" + signUp + '\'' +
                ", leaveReason='" + leaveReason + '\'' +
                ", meetingType='" + meetingType + '\'' +
                ", meetingTime='" + meetingTime + '\'' +
                ", meetingPlace='" + meetingPlace + '\'' +
                ", externalMeetingStatus='" + externalMeetingStatus + '\'' +
                ", meetingRequirement='" + meetingRequirement + '\'' +
                ", leaderShip='" + leaderShip + '\'' +
                ", approverName='" + approverName + '\'' +
                ", newInformation='" + newInformation + '\'' +
                '}';
    }
}
