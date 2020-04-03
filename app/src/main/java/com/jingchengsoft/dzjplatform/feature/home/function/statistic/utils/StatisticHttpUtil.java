package com.jingchengsoft.dzjplatform.feature.home.function.statistic.utils;

import com.jingchengsoft.dzjplatform.http.PretreatmentCallback;
import com.jingchengsoft.dzjplatform.sp.SpUser;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.CallBack;

/**
 * author : wgp
 * time   :  2020/4/2
 * desc   :  统计数据获取
 */
public class StatisticHttpUtil {
    public static void getRiskStatisticData(CallBack callback) {
        EasyHttp.post("apia/v1/analysisManager/getDangerSource")
                .params("token", SpUser.INSTANCE.getToken())
                .execute(callback);
    }

    public static void getDangerStatisticData(PretreatmentCallback callback) {
        EasyHttp.post("apia/v1/projectInspection/hiddenDangerStatistics")
                .params("token", SpUser.INSTANCE.getToken())
                .execute(callback);
    }

    public static void getAccidentPercentStatisticData(PretreatmentCallback callback) {
        EasyHttp.post("apia/v1/compStaticAnalysis/statisticsAccidents")
                .execute(callback);
    }

    public static void getAccidentAnalysisStatisticData(PretreatmentCallback callback) {
        EasyHttp.post("apia/v1/compStaticAnalysis/calendarAccidents")
                .params("year", "2020")
                .execute(callback);
    }

    public static void getAccidentDiedStatisticData(PretreatmentCallback callback) {
        EasyHttp.post("apia/v1/compStaticAnalysis/calendarStatisticalAccidents")
                .params("year", "2020")
                .execute(callback);
    }
}
