package com.jingchengsoft.dzjplatform.feature.home.function.devicemanage.entity;

/**
 * author : wgp
 * time   :  2020/3/19
 * desc   :  设备巡检
 */
public class Inspection {
    private String inspectionName;
    private String writePeople;
    private String phoneNum;
    private String writeDate;

    public String getInspectionName() {
        return inspectionName;
    }

    public void setInspectionName(String inspectionName) {
        this.inspectionName = inspectionName;
    }

    public String getWritePeople() {
        return writePeople;
    }

    public void setWritePeople(String writePeople) {
        this.writePeople = writePeople;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }
}
