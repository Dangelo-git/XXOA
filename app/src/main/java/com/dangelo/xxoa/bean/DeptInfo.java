package com.dangelo.xxoa.bean;

/**
 * Created by dangelo on 16/11/21.
 */
public class DeptInfo {
//    {
//        "CName":"厅领导",
//            "orderCode":1,
//            "orgId":955
//    },
    private String CName;
    private String orderCode;
    private String orgId;
    public boolean select = false;// 本地字段，判断购物车是否选择

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
