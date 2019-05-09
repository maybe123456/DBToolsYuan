package com.example.administrator.dbtools.sport.home.bean;

import java.io.Serializable;

public class SickSubListBean implements Serializable {


    private Integer subId;//": "0101",
    private String subName;//    "subName": "预防保健科"

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }
}
