package com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  安全专项检查实体
 */
public class SafeCheck {
    private String id;
    private String projectName;
    private String checkClassify;
    private String writePeople;
    private String inputPeople;
    private String checkDate;
    private String inputDate;

    public String getWritePeople() {
        return writePeople;
    }

    public void setWritePeople(String writePeople) {
        this.writePeople = writePeople;
    }

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

    public String getCheckClassify() {
        return checkClassify;
    }

    public void setCheckClassify(String checkClassify) {
        this.checkClassify = checkClassify;
    }

    public String getInputPeople() {
        return inputPeople;
    }

    public void setInputPeople(String inputPeople) {
        this.inputPeople = inputPeople;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }
}
