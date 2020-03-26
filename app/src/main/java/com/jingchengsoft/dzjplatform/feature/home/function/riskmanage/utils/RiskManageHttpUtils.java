package com.jingchengsoft.dzjplatform.feature.home.function.riskmanage.utils;

import com.jingchengsoft.dzjplatform.http.PretreatmentCallback;
import com.jingchengsoft.dzjplatform.sp.SpUser;
import com.zhouyou.http.EasyHttp;

/**
 * author : wgp
 * time   :  2020/3/12
 * desc   :  重大危险源获取数据工具类
 */
public class RiskManageHttpUtils {

    public static void getDangerList(String keyName, int start, int length, PretreatmentCallback callback) {
        EasyHttp.post("apia/v1/dangerList/majorDangerList")
                .params("start", String.valueOf(start))
                .params("length", String.valueOf(length))
                .params("token", SpUser.INSTANCE.getToken())
                .execute(callback);
    }

}
