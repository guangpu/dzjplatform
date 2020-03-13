package com.jingchengsoft.dzjplatform.feature.home.function.specialwork.utils;

import com.jingchengsoft.dzjplatform.http.PretreatmentCallback;
import com.jingchengsoft.dzjplatform.sp.SpUser;
import com.zhouyou.http.EasyHttp;

/**
 * author : wgp
 * time   :  2020/3/13
 * desc   :  特种作业网络工具类
 */
public class SpecialWorkHttpUtils {

    /**
     * 获取特种作业人员列表
     * @param name
     * @param start
     * @param length
     * @param callback
     */
    public static void getSpecialWorkList(String name, int start, int length, PretreatmentCallback callback) {
        EasyHttp.post("apia/v1/specialPersonnel/baseList")
                .params("name", name)
                .params("start", String.valueOf(start))
                .params("length", String.valueOf(length))
                .params("token", SpUser.INSTANCE.getToken())
                .execute(callback);
    }

    /**
     * 获取特种作业人员详情
     * @param personId
     * @param start
     * @param length
     * @param callback
     */
    public static void getSpecialWorkDetailList(String personId, int start, int length, PretreatmentCallback callback) {
        EasyHttp.post("apia/v1/specialPersonnel/detailList")
                .params("start", String.valueOf(start))
                .params("length", String.valueOf(length))
                .params("person_id", personId)
                .params("token", SpUser.INSTANCE.getToken())
                .execute(callback);

    }
}
