package com.jingchengsoft.dzjplatform.feature.home.function.riskmanage.entity;

import java.io.Serializable;

/**
 * author : wgp
 * time   :  2020/3/18
 * desc   :  风险源
 */
public class Risk implements Serializable {

    /**
     * plan_end : 2020-03-03
     * update_time : 2020-03-25
     * danger_rs_id : 90000000000009117420318536515584
     * create_time : 2020-03-24
     * actual_start : 2020-03-07
     * plan_start : 2020-03-07
     * actual_end : 2020-03-03
     * id : 90000000000009117420318595235840
     * comp_id : 康蝶
     * creater_id : Administrator
     * danger_rs_des : 掘进臂断裂
     */

    private String plan_end;
    private String update_time;
    private String danger_rs_id;
    private String create_time;
    private String actual_start;
    private String plan_start;
    private String actual_end;
    private String id;
    private String comp_id;
    private String creater_id;
    private String danger_rs_des;

    public String getPlan_end() {
        return plan_end;
    }

    public void setPlan_end(String plan_end) {
        this.plan_end = plan_end;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getDanger_rs_id() {
        return danger_rs_id;
    }

    public void setDanger_rs_id(String danger_rs_id) {
        this.danger_rs_id = danger_rs_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getActual_start() {
        return actual_start;
    }

    public void setActual_start(String actual_start) {
        this.actual_start = actual_start;
    }

    public String getPlan_start() {
        return plan_start;
    }

    public void setPlan_start(String plan_start) {
        this.plan_start = plan_start;
    }

    public String getActual_end() {
        return actual_end;
    }

    public void setActual_end(String actual_end) {
        this.actual_end = actual_end;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComp_id() {
        return comp_id;
    }

    public void setComp_id(String comp_id) {
        this.comp_id = comp_id;
    }

    public String getCreater_id() {
        return creater_id;
    }

    public void setCreater_id(String creater_id) {
        this.creater_id = creater_id;
    }

    public String getDanger_rs_des() {
        return danger_rs_des;
    }

    public void setDanger_rs_des(String danger_rs_des) {
        this.danger_rs_des = danger_rs_des;
    }
}
