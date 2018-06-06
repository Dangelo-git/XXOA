package com.dangelo.xxoa.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dangelo on 18/5/7.
 */

public class DataBody implements Serializable {
    private dispatchDocList dispatchDocList;
    private uaInFileTable UaInFileTable;
    private InnerMeetList UaEnrolment;
    private ExMeetList ExternalMeeting;
    private List<node> PackInfo;
    private List<currentStation> currentStation;
    private List<knowledgeCategories> aList;
    private knowledgeUploadFiles knowledgeUploadFiles;
    private messageNum messageNumAll;
    private String userKey;
    private noticeList noticeList;

    public com.dangelo.xxoa.bean.noticeList getNoticeList() {
        return noticeList;
    }

    public void setNoticeList(com.dangelo.xxoa.bean.noticeList noticeList) {
        this.noticeList = noticeList;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public messageNum getMessageNumAll() {
        return messageNumAll;
    }

    public void setMessageNumAll(messageNum messageNumAll) {
        this.messageNumAll = messageNumAll;
    }

    public knowledgeUploadFiles getKnowledgeUploadFiles() {
        return knowledgeUploadFiles;
    }

    public void setKnowledgeUploadFiles(knowledgeUploadFiles knowledgeUploadFiles) {
        this.knowledgeUploadFiles = knowledgeUploadFiles;
    }

    public List<knowledgeCategories> getaList() {
        return aList;
    }

    public void setaList(List<knowledgeCategories> aList) {
        this.aList = aList;
    }

    public List<node> getPackInfo() {
        return PackInfo;
    }

    public void setPackInfo(List<node> packInfo) {
        PackInfo = packInfo;
    }

    public List<com.dangelo.xxoa.bean.currentStation> getCurrentStation() {
        return currentStation;
    }

    public void setCurrentStation(List<com.dangelo.xxoa.bean.currentStation> currentStation) {
        this.currentStation = currentStation;
    }

    public ExMeetList getExternalMeeting() {
        return ExternalMeeting;
    }

    public void setExternalMeeting(ExMeetList externalMeeting) {
        ExternalMeeting = externalMeeting;
    }

    public InnerMeetList getUaEnrolment() {
        return UaEnrolment;
    }

    public void setUaEnrolment(InnerMeetList uaEnrolment) {
        UaEnrolment = uaEnrolment;
    }

    public uaInFileTable getUaInFileTable() {
        return UaInFileTable;
    }

    public void setUaInFileTable(uaInFileTable uaInFileTable) {
        UaInFileTable = uaInFileTable;
    }

    public dispatchDocList getDispatchDocList() {
        return dispatchDocList;
    }

    public void setDispatchDocList(dispatchDocList dispatchDocList) {
        this.dispatchDocList = dispatchDocList;
    }
}
