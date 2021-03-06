package com.dangelo.xxoa.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "MESSAGE_INFO".
 */
public class MessageInfo {

    private Long id;
    private String msg_name;
    private String msg_content;
    private Integer msg_isread;
    private Long msg_time;
    private Integer msg_type;
    private String msg_pic;
    private String msg_number;
    private String msg_arg1;
    private String msg_arg2;

    public MessageInfo() {
    }

    public MessageInfo(Long id) {
        this.id = id;
    }

    public MessageInfo(Long id, String msg_name, String msg_content, Integer msg_isread, Long msg_time, Integer msg_type, String msg_pic, String msg_number, String msg_arg1, String msg_arg2) {
        this.id = id;
        this.msg_name = msg_name;
        this.msg_content = msg_content;
        this.msg_isread = msg_isread;
        this.msg_time = msg_time;
        this.msg_type = msg_type;
        this.msg_pic = msg_pic;
        this.msg_number = msg_number;
        this.msg_arg1 = msg_arg1;
        this.msg_arg2 = msg_arg2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg_name() {
        return msg_name;
    }

    public void setMsg_name(String msg_name) {
        this.msg_name = msg_name;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    public Integer getMsg_isread() {
        return msg_isread;
    }

    public void setMsg_isread(Integer msg_isread) {
        this.msg_isread = msg_isread;
    }

    public Long getMsg_time() {
        return msg_time;
    }

    public void setMsg_time(Long msg_time) {
        this.msg_time = msg_time;
    }

    public Integer getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(Integer msg_type) {
        this.msg_type = msg_type;
    }

    public String getMsg_pic() {
        return msg_pic;
    }

    public void setMsg_pic(String msg_pic) {
        this.msg_pic = msg_pic;
    }

    public String getMsg_number() {
        return msg_number;
    }

    public void setMsg_number(String msg_number) {
        this.msg_number = msg_number;
    }

    public String getMsg_arg1() {
        return msg_arg1;
    }

    public void setMsg_arg1(String msg_arg1) {
        this.msg_arg1 = msg_arg1;
    }

    public String getMsg_arg2() {
        return msg_arg2;
    }

    public void setMsg_arg2(String msg_arg2) {
        this.msg_arg2 = msg_arg2;
    }

}
