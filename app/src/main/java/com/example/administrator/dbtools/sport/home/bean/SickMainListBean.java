package com.example.administrator.dbtools.sport.home.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SickMainListBean implements Serializable {


        private Integer typeId;
        private String  typeName;      //"typeName": "其他",
        private ArrayList<SickSubListBean> subList;

        public Integer getTypeId() {
                return typeId;
        }

        public void setTypeId(Integer typeId) {
                this.typeId = typeId;
        }

        public String getTypeName() {
                return typeName;
        }

        public void setTypeName(String typeName) {
                this.typeName = typeName;
        }

        public List<SickSubListBean> getSubList() {
                return subList;
        }

        public void setSubList(ArrayList<SickSubListBean> subList) {
                this.subList = subList;
        }
}
