package com.dangelo.xxoa.model;

/**
 * Created by dangelo on 16/11/16.
 */
public class Config {
    // {name:名称; kefuEmail:客服邮箱; version:版本号;details:关于软件;intro:简介;androidv: android版本; androidvdurl: android下载地址;}
    public String id; // 1,
    public String no; // "",
    public String name; // "爱艺考",
    public String intro; // "0",
    public String details; // "<span>1.爱艺考是一款艺考生学习考试管理软件此软件使用终身免费，它使用爱艺考这款APP的.....",
    public String kefuEmail; // "ghostlishen@gmail.com",
    public String version; // "BETA1.0",
    public String status; // 1,
    public String state; // 1
    public String androidv; // 1
    public String androidvdurl; // 1

    public String getAndroidv() {
        return androidv;
    }

    public void setAndroidv(String androidv) {
        this.androidv = androidv;
    }

    public String getAndroidvdurl() {
        return androidvdurl;
    }

    public void setAndroidvdurl(String androidvdurl) {
        this.androidvdurl = androidvdurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getKefuEmail() {
        return kefuEmail;
    }

    public void setKefuEmail(String kefuEmail) {
        this.kefuEmail = kefuEmail;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
