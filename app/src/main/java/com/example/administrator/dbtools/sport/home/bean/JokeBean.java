package com.example.administrator.dbtools.sport.home.bean;

import java.util.List;

public class JokeBean {


    private Integer maxResult;      // "maxResult": 20,
    private Integer allNum;     //       "allNum": 37748,
    private Integer allPages;     //        "allPages": 1888,
    private Integer currentPage;    //         "currentPage": 2,
    private Integer ret_code;     //         "ret_code": 0
    private List<JokeBeanList> contentlist;

    public Integer getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(Integer maxResult) {
        this.maxResult = maxResult;
    }

    public Integer getAllNum() {
        return allNum;
    }

    public void setAllNum(Integer allNum) {
        this.allNum = allNum;
    }

    public Integer getAllPages() {
        return allPages;
    }

    public void setAllPages(Integer allPages) {
        this.allPages = allPages;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getRet_code() {
        return ret_code;
    }

    public void setRet_code(Integer ret_code) {
        this.ret_code = ret_code;
    }

    public List<JokeBeanList> getContentlist() {
        return contentlist;
    }

    public void setContentlist(List<JokeBeanList> contentlist) {
        this.contentlist = contentlist;
    }
}
