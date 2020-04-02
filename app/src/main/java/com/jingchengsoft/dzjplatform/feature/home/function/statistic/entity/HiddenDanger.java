package com.jingchengsoft.dzjplatform.feature.home.function.statistic.entity;

/**
 * author : wgp
 * time   :  2020/4/2
 * desc   :  隐患统计实体
 */
public class HiddenDanger {

    /**
     * rccount : 1
     * aqzxcount : 0
     * project_name : 专项测试项目1
     * lddbcount : 0
     */

    private int rccount;
    private int aqzxcount;
    private String project_name;
    private int lddbcount;

    public int getRccount() {
        return rccount;
    }

    public void setRccount(int rccount) {
        this.rccount = rccount;
    }

    public int getAqzxcount() {
        return aqzxcount;
    }

    public void setAqzxcount(int aqzxcount) {
        this.aqzxcount = aqzxcount;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public int getLddbcount() {
        return lddbcount;
    }

    public void setLddbcount(int lddbcount) {
        this.lddbcount = lddbcount;
    }
}
