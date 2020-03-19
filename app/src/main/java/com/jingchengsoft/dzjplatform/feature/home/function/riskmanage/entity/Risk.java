package com.jingchengsoft.dzjplatform.feature.home.function.riskmanage.entity;

/**
 * author : wgp
 * time   :  2020/3/18
 * desc   :  风险源
 */
public class Risk {
    private String projectName;
    private String reportDate;
    private String reportPeople;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportPeople() {
        return reportPeople;
    }

    public void setReportPeople(String reportPeople) {
        this.reportPeople = reportPeople;
    }
}
