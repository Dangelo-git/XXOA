package com.dangelo.xxoa.bean;

import java.io.Serializable;

/**
 * Created by dangelo on 16/12/19.
 */
public class noticePubFile implements Serializable {
    /**
     *id 通知公告附件ID
     noticePublicId 通知公告ID
     fileName 文件名称
     filePath 文件保存路径
     */
    private String id;
    private String noticePublicId;
    private String fileName;
    private String filePath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoticePublicId() {
        return noticePublicId;
    }

    public void setNoticePublicId(String noticePublicId) {
        this.noticePublicId = noticePublicId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
