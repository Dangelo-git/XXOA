package com.dangelo.xxoa.bean;

import java.io.Serializable;

/**
 * Created by dangelo on 18/5/9.
 */

public class knowledgeCategories implements Serializable {
//     "id":12,
//             "knowledgeType":2,
//             "knowledgeCategory":"个人一",
//             "knowledgeDept":"测试",
//             "knowledgeSum":4,
//             "operatorUserId":5,
//             "knowledgeCreateTime":1524274377000,
//             "knowledgeUpdateTime":1524460269000,
//             "knowledgeSort":"1"

    private String id;
    private String knowledgeType;
    private String knowledgeCategory;
    private String knowledgeDept;
    private String knowledgeSum;
    private String operatorUserId;
    private String knowledgeCreateTime;
    private String knowledgeUpdateTime;
    private String knowledgeSort;

    @Override
    public String toString() {
        return "knowledgeCategories{" +
                "id='" + id + '\'' +
                ", knowledgeType='" + knowledgeType + '\'' +
                ", knowledgeCategory='" + knowledgeCategory + '\'' +
                ", knowledgeDept='" + knowledgeDept + '\'' +
                ", knowledgeSum='" + knowledgeSum + '\'' +
                ", operatorUserId='" + operatorUserId + '\'' +
                ", knowledgeCreateTime='" + knowledgeCreateTime + '\'' +
                ", knowledgeUpdateTime='" + knowledgeUpdateTime + '\'' +
                ", knowledgeSort='" + knowledgeSort + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKnowledgeType() {
        return knowledgeType;
    }

    public void setKnowledgeType(String knowledgeType) {
        this.knowledgeType = knowledgeType;
    }

    public String getKnowledgeCategory() {
        return knowledgeCategory;
    }

    public void setKnowledgeCategory(String knowledgeCategory) {
        this.knowledgeCategory = knowledgeCategory;
    }

    public String getKnowledgeDept() {
        return knowledgeDept;
    }

    public void setKnowledgeDept(String knowledgeDept) {
        this.knowledgeDept = knowledgeDept;
    }

    public String getKnowledgeSum() {
        return knowledgeSum;
    }

    public void setKnowledgeSum(String knowledgeSum) {
        this.knowledgeSum = knowledgeSum;
    }

    public String getOperatorUserId() {
        return operatorUserId;
    }

    public void setOperatorUserId(String operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    public String getKnowledgeCreateTime() {
        return knowledgeCreateTime;
    }

    public void setKnowledgeCreateTime(String knowledgeCreateTime) {
        this.knowledgeCreateTime = knowledgeCreateTime;
    }

    public String getKnowledgeUpdateTime() {
        return knowledgeUpdateTime;
    }

    public void setKnowledgeUpdateTime(String knowledgeUpdateTime) {
        this.knowledgeUpdateTime = knowledgeUpdateTime;
    }

    public String getKnowledgeSort() {
        return knowledgeSort;
    }

    public void setKnowledgeSort(String knowledgeSort) {
        this.knowledgeSort = knowledgeSort;
    }
}
