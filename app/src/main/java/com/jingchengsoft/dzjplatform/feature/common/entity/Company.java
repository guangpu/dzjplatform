package com.jingchengsoft.dzjplatform.feature.common.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * author : wgp
 * time   :  2020/3/10
 * desc   :  单位
 */
public class Company {
    private String code;
    private String name;

    public Company(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static List<Company> defaultList() {
        List<Company> dataList = new ArrayList<>();
        Company company = new Company("012000030", "煤炭地质总局");
        dataList.add(company);
        return dataList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
