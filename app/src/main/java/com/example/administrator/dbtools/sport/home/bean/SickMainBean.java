package com.example.administrator.dbtools.sport.home.bean;

import java.io.Serializable;
import java.util.List;

public class SickMainBean implements Serializable {


        private Integer ret_code;
        private List<SickMainListBean> list;

    public Integer getRet_code() {
        return ret_code;
    }

    public void setRet_code(Integer ret_code) {
        this.ret_code = ret_code;
    }

    public List<SickMainListBean> getList() {
        return list;
    }

    public void setList(List<SickMainListBean> list) {
        this.list = list;
    }
}
