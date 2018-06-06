package com.dangelo.xxoa.bean;

import java.util.List;

/**
 * Created by dangelo on 16/12/19.
 */
public class wsMeetingApply {
    /**
     * <wsMeetingApply memo='会议信息申请'>
     <uuEName memo='用户名'>xtgly</uuEName>
     <meetList memo='会议基础信息'>
     <meetName memo='会议标题'></meetName>
     <meetSite memo='会议地点'></meetSite>
     <meetType memo='会议类型'></meetType>
     <num memo='类型次数'>5</num>
     <Year memo='年份'>2016</Year>
     <meetDate memo='开始时间'>2016-12-25T00:00:00+08:00</meetDate>
     <endDate memo='截止时间'>2016-11-25T00:00:00+08:00</endDate>
     <Status memo='状态'>1</Status>
     <charge memo='主持人'></charge>
     <contacts memo='联系人'></contacts>
     <phone memo='联系方式'>123456</phone>
     <attendOne memo='出席人员'></attendOne>
     </meetList>
     <topics memo='议题基础信息'>
     <topicName memo='议题标题'></topicName>
     <attendDept memo='列席人员'></attendDept>
     <orderNo memo='排序码'>2</orderNo>
     <reportDept memo='汇报单位'></reportDept>
     <reportDeptId memo='汇报单位id'></reportDeptId>
     </topics>
     <topics memo='议题基础信息'>
     <topicName memo='议题标题'>
     </topicName><attendDept memo='列席人员'>
     </attendDept><orderNo memo='排序码'>2</orderNo>
     <reportDept memo='汇报单位'></reportDept>
     <reportDeptId memo='汇报单位id'></reportDeptId>
     </topics>
     */
    private String uuEName;
    private meetList meetList;
    private List<Topics> topics;

    public String getUuEName() {
        return uuEName;
    }

    public void setUuEName(String uuEName) {
        this.uuEName = uuEName;
    }

    public com.dangelo.xxoa.bean.meetList getMeetList() {
        return meetList;
    }

    public void setMeetList(com.dangelo.xxoa.bean.meetList meetList) {
        this.meetList = meetList;
    }

    public List<Topics> getTopics() {
        return topics;
    }

    public void setTopics(List<Topics> topics) {
        this.topics = topics;
    }
}
