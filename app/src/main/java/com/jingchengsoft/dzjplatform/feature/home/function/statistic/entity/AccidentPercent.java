package com.jingchengsoft.dzjplatform.feature.home.function.statistic.entity;

/**
 * author : wgp
 * time   :  2020/4/2
 * desc   :  事故占比
 */
public class AccidentPercent {

    /**
     * fxs : 2
     * catagory : 触电事故
     */

    private int fxs;
    private String catagory;

    public int getFxs() {
        return fxs;
    }

    public void setFxs(int fxs) {
        this.fxs = fxs;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }
}
