package com.dangelo.xxoa.bean;

/**
 * Created by dangelo on 16/9/29.
 */
public class user {

    private String userEnglishName;
    private String userPhoneImeid;//套餐有效期
    private String passWord;//本地 ,密码
    private String registrationId;//本地 ,密码
    private String userKey;//本地 ,密码

    public String getUSER_KEY() {
        return userKey;
    }

    public void setUSER_KEY(String userKey) {
        this.userKey = userKey;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getUserEnglishName() {
        return userEnglishName;
    }



    public void setUserEnglishName(String userEnglishName) {
        this.userEnglishName = userEnglishName;
    }

    public String getUserPhoneImeid() {
        return userPhoneImeid;
    }

    public void setUserPhoneImeid(String userPhoneImeid) {
        this.userPhoneImeid = userPhoneImeid;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
