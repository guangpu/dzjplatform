package com.jingchengsoft.dzjplatform.feature.home.function.statistic.entity;

/**
 * author : wgp
 * time   :  2020/4/1
 * desc   :
 */
public class RiskDanger {

    /**
     * project_id : 256
     * project_name : 256
     * majordangercount : 2
     */

    private String project_id;
    private String project_name;
    private int dangercount;
    private int majordangercount;

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public int getMajordangercount() {
        return majordangercount;
    }

    public void setMajordangercount(int majordangercount) {
        this.majordangercount = majordangercount;
    }

    public int getDangercount() {
        return dangercount;
    }

    public void setDangercount(int dangercount) {
        this.dangercount = dangercount;
    }
}
