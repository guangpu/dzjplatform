package com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity;

/**
 * author : wgp
 * time   :  2020/3/13
 * desc   :  安全监督日志
 */
public class SafeLog {
    private String id;
    private String projectName;
    private String weatherStatus;
    private String tstdStatus;
    private String writePeople;
    private String writeDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getWeatherStatus() {
        return weatherStatus;
    }

    public void setWeatherStatus(String weatherStatus) {
        this.weatherStatus = weatherStatus;
    }

    public String getTstdStatus() {
        return tstdStatus;
    }

    public void setTstdStatus(String tstdStatus) {
        this.tstdStatus = tstdStatus;
    }

    public String getWritePeople() {
        return writePeople;
    }

    public void setWritePeople(String writePeople) {
        this.writePeople = writePeople;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }
}
