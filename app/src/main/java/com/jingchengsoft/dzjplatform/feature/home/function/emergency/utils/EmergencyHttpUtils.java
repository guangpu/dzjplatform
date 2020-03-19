package com.jingchengsoft.dzjplatform.feature.home.function.emergency.utils;

import com.jingchengsoft.dzjplatform.http.PretreatmentCallback;
import com.jingchengsoft.dzjplatform.sp.SpUser;
import com.zhouyou.http.EasyHttp;

/**
 * author : wgp
 * time   :  2020/3/19
 * desc   :  应急http访问
 */
public class EmergencyHttpUtils {

    /**
     * 获取演练计划列表
     * @param keyWord
     * @param start
     * @param length
     * @param callback
     */
    public static void getPractisePlanList(String keyWord, int start, int length, PretreatmentCallback callback) {
        EasyHttp.post("apia/v1/contingencyManagement/listExerciseplan")
                .params("keyWord", keyWord)
                .params("start", String.valueOf(start))
                .params("length", String.valueOf(length))
                .params("token", SpUser.INSTANCE.getToken())
                .execute(callback);
    }

    /**
     * 获取演练记录列表
     * @param keyWord
     * @param start
     * @param length
     * @param callback
     */
    public static void getPractiseRecordList(String keyWord, int start, int length, PretreatmentCallback callback) {
        EasyHttp.post("apia/v1/contingencyManagement/listRecord")
                .params("keyWord", keyWord)
                .params("start", String.valueOf(start))
                .params("length", String.valueOf(length))
                .params("token", SpUser.INSTANCE.getToken())
                .execute(callback);
    }

    /**
     * 获取演练评价列表
     * @param keyWord
     * @param start
     * @param length
     * @param callback
     */
    public static void getPractiseCommentList(String keyWord, int start, int length, PretreatmentCallback callback) {
        EasyHttp.post("apia/v1/contingencyManagement/listEvaluate")
                .params("keyWord", keyWord)
                .params("start", String.valueOf(start))
                .params("length", String.valueOf(length))
                .params("token", SpUser.INSTANCE.getToken())
                .execute(callback);
    }
}
