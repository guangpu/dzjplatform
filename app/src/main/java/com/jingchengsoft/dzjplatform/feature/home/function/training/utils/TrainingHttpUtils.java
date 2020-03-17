package com.jingchengsoft.dzjplatform.feature.home.function.training.utils;

import com.jingchengsoft.dzjplatform.http.PretreatmentCallback;
import com.jingchengsoft.dzjplatform.sp.SpUser;
import com.zhouyou.http.EasyHttp;

/**
 * author : wgp
 * time   :  2020/3/16
 * desc   :  培训网络访问类
 */
public class TrainingHttpUtils {
    /**
     * 获取培训人员列表
     * @param name
     * @param start
     * @param length
     * @param callback
     */
    public static void getTraineeList(String name, int start, int length, PretreatmentCallback callback) {
        EasyHttp.post("apia/v1/trainee/getTraineeFileList")
                .params("name", name)
                .params("start", String.valueOf(start))
                .params("length", String.valueOf(length))
                .params("token", SpUser.INSTANCE.getToken())
                .execute(callback);
    }

    /**
     * 获取培训人员详情
     * @param id
     * @param callback
     */
    public static void getTraineeDetail(String id, PretreatmentCallback callback) {
        EasyHttp.post("apia/v1/planManage/getOneTrainee")
                .params("id", id)
                .params("token", SpUser.INSTANCE.getToken())
                .execute(callback);
    }
}
