package com.dangelo.xxoa.bean;

import java.io.Serializable;

/**
 * Created by dangelo on 18/5/9.
 */

public class knowledgePersonal implements Serializable {
//"id":252,
//        "uploadFileName":"10.jpg",
//        "uploadFilePath":"/root/filewebapp/filedownload/knowledge/",
//        "upFileUserId":5,"upFileCategoryName":"个人一",
//        "knowledgeCategoryId":null,
//        "uploadFileCreateTime":1524460491000,
//        "uploadFileDownloadSum":0,
//        "uploadFileDelete":1,
//        "uploadFileUserName":"超人",
//        "uploadFileDeptName":"测试",
//        "uploadFileSaveName":"admin_10_1524460491524.jpg",
//        "saveOperateUserid":5


    private String id;
    private String uploadFileName;
    private String uploadFilePath;
    private String upFileUserId;
    private String knowledgeCategoryId;
    private String uploadFileCreateTime;
    private String uploadFileDownloadSum;
    private String uploadFileDelete;
    private String uploadFileUserName;
    private String uploadFileDeptName;
    private String uploadFileSaveName;
    private String saveOperateUserid;
    private String upFileCategoryName;

    public String getUpFileCategoryName() {
        return upFileCategoryName;
    }

    public void setUpFileCategoryName(String upFileCategoryName) {
        this.upFileCategoryName = upFileCategoryName;
    }

    @Override
    public String toString() {
        return "knowledgePersonal{" +
                "id='" + id + '\'' +
                ", uploadFileName='" + uploadFileName + '\'' +
                ", uploadFilePath='" + uploadFilePath + '\'' +
                ", upFileUserId='" + upFileUserId + '\'' +
                ", knowledgeCategoryId='" + knowledgeCategoryId + '\'' +
                ", uploadFileCreateTime='" + uploadFileCreateTime + '\'' +
                ", uploadFileDownloadSum='" + uploadFileDownloadSum + '\'' +
                ", uploadFileDelete='" + uploadFileDelete + '\'' +
                ", uploadFileUserName='" + uploadFileUserName + '\'' +
                ", uploadFileDeptName='" + uploadFileDeptName + '\'' +
                ", uploadFileSaveName='" + uploadFileSaveName + '\'' +
                ", saveOperateUserid='" + saveOperateUserid + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadFilePath() {
        return uploadFilePath;
    }

    public void setUploadFilePath(String uploadFilePath) {
        this.uploadFilePath = uploadFilePath;
    }

    public String getUpFileUserId() {
        return upFileUserId;
    }

    public void setUpFileUserId(String upFileUserId) {
        this.upFileUserId = upFileUserId;
    }

    public String getKnowledgeCategoryId() {
        return knowledgeCategoryId;
    }

    public void setKnowledgeCategoryId(String knowledgeCategoryId) {
        this.knowledgeCategoryId = knowledgeCategoryId;
    }

    public String getUploadFileCreateTime() {
        return uploadFileCreateTime;
    }

    public void setUploadFileCreateTime(String uploadFileCreateTime) {
        this.uploadFileCreateTime = uploadFileCreateTime;
    }

    public String getUploadFileDownloadSum() {
        return uploadFileDownloadSum;
    }

    public void setUploadFileDownloadSum(String uploadFileDownloadSum) {
        this.uploadFileDownloadSum = uploadFileDownloadSum;
    }

    public String getUploadFileDelete() {
        return uploadFileDelete;
    }

    public void setUploadFileDelete(String uploadFileDelete) {
        this.uploadFileDelete = uploadFileDelete;
    }

    public String getUploadFileUserName() {
        return uploadFileUserName;
    }

    public void setUploadFileUserName(String uploadFileUserName) {
        this.uploadFileUserName = uploadFileUserName;
    }

    public String getUploadFileDeptName() {
        return uploadFileDeptName;
    }

    public void setUploadFileDeptName(String uploadFileDeptName) {
        this.uploadFileDeptName = uploadFileDeptName;
    }

    public String getUploadFileSaveName() {
        return uploadFileSaveName;
    }

    public void setUploadFileSaveName(String uploadFileSaveName) {
        this.uploadFileSaveName = uploadFileSaveName;
    }

    public String getSaveOperateUserid() {
        return saveOperateUserid;
    }

    public void setSaveOperateUserid(String saveOperateUserid) {
        this.saveOperateUserid = saveOperateUserid;
    }
}
