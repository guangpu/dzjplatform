package com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.utils;

import com.jingchengsoft.dzjplatform.http.PretreatmentCallback;
import com.jingchengsoft.dzjplatform.sp.SpUser;
import com.zhouyou.http.EasyHttp;

/**
 * author : wgp
 * time   :  2020/3/12
 * desc   :  隐患排查获取数据工具类
 */
public class HiddenCheckHttpUtils {

    /**
     * 获取领导带班检查列表
     * @param keyWord
     * @param start
     * @param length
     * @param callback
     */
    public static void getLeaderCheckList(String keyWord, int start, int length, PretreatmentCallback callback) {
        EasyHttp.post("apia/v1/projectInspection/list")
                .params("keyWord", keyWord)
                .params("start", String.valueOf(start))
                .params("length", String.valueOf(length))
                .params("token", SpUser.INSTANCE.getToken())
                .params("inspection_type", "1")
                .execute(callback);
    }
}
