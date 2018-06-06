package com.dangelo.xxoa.bean;

import java.io.Serializable;

/**
 * Created by dangelo on 18/5/9.
 */

public class Rqtknowledge implements Serializable {
    private String knowledgeType;
    private String knowledgeCategoryId;
    private String id;
    private String knowledgeCategory;
    private int page;
    private int rows = 50;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getKnowledgeType() {
        return knowledgeType;
    }

    public void setKnowledgeType(String knowledgeType) {
        this.knowledgeType = knowledgeType;
    }

    public String getKnowledgeCategoryId() {
        return knowledgeCategoryId;
    }

    public void setKnowledgeCategoryId(String knowledgeCategoryId) {
        this.knowledgeCategoryId = knowledgeCategoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKnowledgeCategory() {
        return knowledgeCategory;
    }

    public void setKnowledgeCategory(String knowledgeCategory) {
        this.knowledgeCategory = knowledgeCategory;
    }
}
