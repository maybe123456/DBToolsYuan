package com.example.administrator.dbtools.sport.home.bean;

public class SpinnerBean {

    private String nameSpinner;

    private String codeSpinner;

    public SpinnerBean(String nameSpinner, String codeSpinner) {
        this.nameSpinner = nameSpinner;
        this.codeSpinner = codeSpinner;
    }

    public String getNameSpinner() {
        return nameSpinner;
    }

    public void setNameSpinner(String nameSpinner) {
        this.nameSpinner = nameSpinner;
    }

    public String getCodeSpinner() {
        return codeSpinner;
    }

    public void setCodeSpinner(String codeSpinner) {
        this.codeSpinner = codeSpinner;
    }
}
