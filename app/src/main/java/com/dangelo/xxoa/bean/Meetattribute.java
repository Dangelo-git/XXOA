package com.dangelo.xxoa.bean;

/**
 * Created by dangelo on 16/11/21.
 */
public class Meetattribute {
//    {
//        "attributeName":"测试一",
//            "attributeValue":"测试一",
//            "id":"407"
//    }
    private String attributeName;//名称
    private String attributeValue;//值
    private String id;

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
