package com.dangelo.xxoa.bean;

/**
 * Created by dangelo on 16/12/19.
 */
public class typeCounts {
//    <typeCounts memo='会议类型次数信息'>
//    <attributeName memo='名称'>专题会议</attributeName>
//    <count memo='次数'> </count>
//    <id memo='id'> </id>
//    </typeCounts>

    private String attributeName;//名称
    private String attributeValue;//值
    private String id;
    private String count;

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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
