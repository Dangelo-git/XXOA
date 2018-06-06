package com.dangelo.xxoa.bean;

import java.io.Serializable;

/**
 * Created by dangelo on 16/9/29.
 */
public class docinfo implements Serializable {
//    "docId":4830,
//            "docTitle":"12312",
//            "draftTitle":"西藏自治区交通运输厅文件发文稿纸",
//            "princDeptName":"通信信息中心",
//            "creatorName":"系统管理员",
//            "telephone":"123",
//            "secretLevel":"主动公开",
//            "docPath":"1$4830$Doc.doc",
//            "createDate":"2016年10月25日",
//            "copySend":"1231",
//            "mainSend":"123123"

    private String docId;
    private String docTitle;
    private String draftTitle;
    private String docNo;//套餐有效期
    private String princDeptName;
    private String creatorName;
    private String telephone;
    private String secretLevel;
    private String docPath;
    private String createDate;
    private String copySend;
    private String mainSend;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getDraftTitle() {
        return draftTitle;
    }

    public void setDraftTitle(String draftTitle) {
        this.draftTitle = draftTitle;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getPrincDeptName() {
        return princDeptName;
    }

    public void setPrincDeptName(String princDeptName) {
        this.princDeptName = princDeptName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSecretLevel() {
        return secretLevel;
    }

    public void setSecretLevel(String secretLevel) {
        this.secretLevel = secretLevel;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCopySend() {
        return copySend;
    }

    public void setCopySend(String copySend) {
        this.copySend = copySend;
    }

    public String getMainSend() {
        return mainSend;
    }

    public void setMainSend(String mainSend) {
        this.mainSend = mainSend;
    }
}
