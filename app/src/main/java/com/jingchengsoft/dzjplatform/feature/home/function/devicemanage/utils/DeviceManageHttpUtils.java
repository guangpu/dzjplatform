package com.jingchengsoft.dzjplatform.feature.home.function.devicemanage.utils;

import com.jingchengsoft.dzjplatform.http.PretreatmentCallback;
import com.jingchengsoft.dzjplatform.sp.SpUser;
import com.zhouyou.http.EasyHttp;

/**
 * author : wgp
 * time   :  2020/3/30
 * desc   :  设备管理网络访问类
 */
public class DeviceManageHttpUtils {

    /**
     * 设备巡检列表
     * @param start
     * @param length
     * @param callback
     */
    public static void getInspectionList(String keyValue, int start, int length, PretreatmentCallback callback) {
        EasyHttp.post("apia/v1/equipManager/equipmentInspectionList")
                .params("start", String.valueOf(start))
                .params("length", String.valueOf(length))
                .params("token", SpUser.INSTANCE.getToken())
                .execute(callback);
    }
}
