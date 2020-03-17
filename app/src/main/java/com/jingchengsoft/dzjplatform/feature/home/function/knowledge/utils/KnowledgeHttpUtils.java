package com.jingchengsoft.dzjplatform.feature.home.function.knowledge.utils;

import com.jingchengsoft.dzjplatform.http.PretreatmentCallback;
import com.jingchengsoft.dzjplatform.sp.SpUser;
import com.zhouyou.http.EasyHttp;

/**
 * author : wgp
 * time   :  2020/3/12
 * desc   :  知识资源获取数据工具类
 */
public class KnowledgeHttpUtils {
    /**
     * 获取知识资源列表
     * @param keyName
     * @param start
     * @param length
     * @param callback
     */
    public static void getKnowledgeList(String keyName, int start, int length, PretreatmentCallback callback) {
        EasyHttp.post("apia/v1/knowledgeManager/list")
                .params("keyName", keyName)
                .params("start", String.valueOf(start))
                .params("length", String.valueOf(length))
                .params("token", SpUser.INSTANCE.getToken())
                .execute(callback);
    }
}
