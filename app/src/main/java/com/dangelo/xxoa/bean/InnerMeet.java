package com.dangelo.xxoa.bean;

import java.io.Serializable;

/**
 * Created by dangelo on 18/5/9.
 */

public class InnerMeet implements Serializable {
    private String id;
    private String conferenceInfoId;
    private String userId;
    private String userName;
    private String reportingIssues;
    private String topicMaterialPath;
    private String status;
    private String disc;
    private String stopTime;
    private String conferenceName;
    private String conferenceType;
    private String conferenceTime;
    private String meetingName;
    private String conferenceInfoStatus;
    private String signUp;
    private String operatorUserId;
    private String newInformation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConferenceInfoId() {
        return conferenceInfoId;
    }

    public void setConferenceInfoId(String conferenceInfoId) {
        this.conferenceInfoId = conferenceInfoId;
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

    public String getReportingIssues() {
        return reportingIssues;
    }

    public void setReportingIssues(String reportingIssues) {
        this.reportingIssues = reportingIssues;
    }

    public String getTopicMaterialPath() {
        return topicMaterialPath;
    }

    public void setTopicMaterialPath(String topicMaterialPath) {
        this.topicMaterialPath = topicMaterialPath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public String getConferenceType() {
        return conferenceType;
    }

    public void setConferenceType(String conferenceType) {
        this.conferenceType = conferenceType;
    }

    public String getConferenceTime() {
        return conferenceTime;
    }

    public void setConferenceTime(String conferenceTime) {
        this.conferenceTime = conferenceTime;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getConferenceInfoStatus() {
        return conferenceInfoStatus;
    }

    public void setConferenceInfoStatus(String conferenceInfoStatus) {
        this.conferenceInfoStatus = conferenceInfoStatus;
    }

    public String getSignUp() {
        return signUp;
    }

    public void setSignUp(String signUp) {
        this.signUp = signUp;
    }

    public String getOperatorUserId() {
        return operatorUserId;
    }

    public void setOperatorUserId(String operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    public String getNewInformation() {
        return newInformation;
    }

    public void setNewInformation(String newInformation) {
        this.newInformation = newInformation;
    }

    @Override
    public String toString() {
        return "InnerMeet{" +
                "id='" + id + '\'' +
                ", conferenceInfoId='" + conferenceInfoId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", reportingIssues='" + reportingIssues + '\'' +
                ", topicMaterialPath='" + topicMaterialPath + '\'' +
                ", status='" + status + '\'' +
                ", disc='" + disc + '\'' +
                ", stopTime='" + stopTime + '\'' +
                ", conferenceName='" + conferenceName + '\'' +
                ", conferenceType='" + conferenceType + '\'' +
                ", conferenceTime='" + conferenceTime + '\'' +
                ", meetingName='" + meetingName + '\'' +
                ", conferenceInfoStatus='" + conferenceInfoStatus + '\'' +
                ", signUp='" + signUp + '\'' +
                ", operatorUserId='" + operatorUserId + '\'' +
                ", newInformation='" + newInformation + '\'' +
                '}';
    }
}
