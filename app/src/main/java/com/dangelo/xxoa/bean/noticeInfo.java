package com.dangelo.xxoa.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dangelo on 16/12/19.
 */
public class noticeInfo implements Serializable {
    /**
     * "id":14,
     "notice":"测试通知公告04",
     "noticeContent":"测试通知公告测试通知公告测试通知公告",
     "issuer":"公告管理员",
     "issuerId":171,
     "issueDate":1523419503000,
     "noticeState":null,
     "noticePubFileList":null
     */
    private String id;
    private String notice;
    private String noticeContent;
    private String issuer;
    private String issuerId;
    private String noticeState;
    private String issueDate;
    private List<noticePubFile> noticePubFileList;

    public List<noticePubFile> getNoticePubFileList() {
        return noticePubFileList;
    }

    public void setNoticePubFileList(List<noticePubFile> noticePubFileList) {
        this.noticePubFileList = noticePubFileList;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public String getNoticeState() {
        return noticeState;
    }

    public void setNoticeState(String noticeState) {
        this.noticeState = noticeState;
    }

    @Override
    public String toString() {
        return "noticeInfo{" +
                "id='" + id + '\'' +
                ", notice='" + notice + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", issuer='" + issuer + '\'' +
                ", issuerId='" + issuerId + '\'' +
                ", noticeState='" + noticeState + '\'' +
                ", issueDate='" + issueDate + '\'' +
                ", noticePubFileList=" + noticePubFileList +
                '}';
    }
}
