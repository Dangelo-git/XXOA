package com.dangelo.xxoa.bean;

import java.io.Serializable;

/**
 * Created by dangelo on 18/5/7.
 */

public class nextStation implements Serializable {
    private String conditionText;//	下一步任务签批信息（这步任务和下一步任务之间的连接线）
    private String presonsationid;//	下一步任务接收岗位id
    private Long id;//	主键id
    private String userId;//	下一步审批人id（
    private String option;//	审批意见
    private String linkName;//	环节名称

    public String getConditionText() {
        return conditionText;
    }

    public void setConditionText(String conditionText) {
        this.conditionText = conditionText;
    }

    public String getPresonsationid() {
        return presonsationid;
    }

    public void setPresonsationid(String presonsationid) {
        this.presonsationid = presonsationid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }
}
