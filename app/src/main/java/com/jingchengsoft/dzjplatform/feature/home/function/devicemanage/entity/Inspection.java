package com.jingchengsoft.dzjplatform.feature.home.function.devicemanage.entity;

/**
 * author : wgp
 * time   :  2020/3/19
 * desc   :  设备巡检
 */
public class Inspection {

    /**
     * create_time : 2020-3-1
     * name : 张三
     * mobile : 15897895686
     * index : 1
     * id : 1
     * content : 钻机巡检记录
     */

    private String create_time;
    private String name;
    private String mobile;
    private String id;
    private String content;

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
