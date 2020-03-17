package com.jingchengsoft.dzjplatform.feature.home.function.training.entity;

/**
 * author : wgp
 * time   :  2020/3/16
 * desc   :  人员详情
 */
public class TraineeDetail extends Trainee {

    /**
     * end_date : 2020/03/09
     * create_time : 2020-03-16 15:33:39
     * remark :
     * is_special_work : 1
     * creater_id : Administrator
     * is_card : 1
     * special_work_type_id :
     * start_date : 2020/03/03
     */

    private String end_date;
    private String create_time;
    private String remark;
    private String is_special_work;
    private String creater_id;
    private String is_card;
    private String special_work_type_id;
    private String start_date;

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIs_special_work() {
        return is_special_work;
    }

    public void setIs_special_work(String is_special_work) {
        this.is_special_work = is_special_work;
    }

    public String getCreater_id() {
        return creater_id;
    }

    public void setCreater_id(String creater_id) {
        this.creater_id = creater_id;
    }

    public String getIs_card() {
        return is_card;
    }

    public void setIs_card(String is_card) {
        this.is_card = is_card;
    }

    public String getSpecial_work_type_id() {
        return special_work_type_id;
    }

    public void setSpecial_work_type_id(String special_work_type_id) {
        this.special_work_type_id = special_work_type_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }
}
