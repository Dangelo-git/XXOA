package com.dangelo.xxoa.uitl;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/30.
 */
public class Package_list implements Serializable {
    private String package_code;
    private String package_info;
    private String package_price;
    private String package_outlet;//套餐有效期
    private String package_name;
    private String package_startdate;
    private String package_enddate;


    public Package_list() {

    }

    public Package_list(String package_code, String package_info, String package_price, String package_outlet, String package_name, String package_startdate, String package_enddate) {
        this.package_code = package_code;
        this.package_info = package_info;
        this.package_price = package_price;
        this.package_outlet = package_outlet;
        this.package_name = package_name;
        this.package_startdate = package_startdate;
        this.package_enddate = package_enddate;
    }

    public String getPackage_code() {
        return package_code;
    }

    public void setPackage_code(String package_code) {
        this.package_code = package_code;
    }

    public String getPackage_info() {
        return package_info;
    }

    public void setPackage_info(String package_info) {
        this.package_info = package_info;
    }

    public String getPackage_price() {
        return package_price;
    }

    public void setPackage_price(String package_price) {
        this.package_price = package_price;
    }

    public String getPackage_outlet() {
        return package_outlet;
    }

    public void setPackage_outlet(String package_outlet) {
        this.package_outlet = package_outlet;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getPackage_startdate() {
        return package_startdate;
    }

    public void setPackage_startdate(String package_startdate) {
        this.package_startdate = package_startdate;
    }

    public String getPackage_enddate() {
        return package_enddate;
    }

    public void setPackage_enddate(String package_enddate) {
        this.package_enddate = package_enddate;
    }

    @Override
    public String toString() {
        return "Package_list{" +
                "package_code='" + package_code + '\'' +
                ", package_info='" + package_info + '\'' +
                ", package_price='" + package_price + '\'' +
                ", package_outlet='" + package_outlet + '\'' +
                ", package_name='" + package_name + '\'' +
                ", package_startdate='" + package_startdate + '\'' +
                ", package_enddate='" + package_enddate + '\'' +
                '}';
    }
}
