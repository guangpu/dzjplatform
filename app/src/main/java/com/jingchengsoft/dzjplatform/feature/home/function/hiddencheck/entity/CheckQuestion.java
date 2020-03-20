package com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity;

/**
 * author : wgp
 * time   :  2020/3/20
 * desc   :  检查问题
 */
public class CheckQuestion {
    private String id;
    private String desc;
    private String rectifyMethod;
    private String rectifyPeople;
    private String finishDate;
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRectifyMethod() {
        return rectifyMethod;
    }

    public void setRectifyMethod(String rectifyMethod) {
        this.rectifyMethod = rectifyMethod;
    }

    public String getRectifyPeople() {
        return rectifyPeople;
    }

    public void setRectifyPeople(String rectifyPeople) {
        this.rectifyPeople = rectifyPeople;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
