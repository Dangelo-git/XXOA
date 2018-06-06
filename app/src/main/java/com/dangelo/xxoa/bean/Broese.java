package com.dangelo.xxoa.bean;

/**
 * Created by dangelo on 16/11/28.
 */
public class Broese {
//    "id": "ID",
//            "title": "标题",
//            "date": "发布时间",
//            "imgUrl": "图片地址",
//            "content": "内容信息"

    private String title;
    private String date;
    private String id;
    private String imgUrl;
    private String content;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
