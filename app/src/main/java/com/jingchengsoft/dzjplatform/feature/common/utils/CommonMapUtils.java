package com.jingchengsoft.dzjplatform.feature.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * author : wgp
 * time   :  2020/3/17
 * desc   :
 */
public class CommonMapUtils {
    private static Map<String, String> eduMap = new HashMap<>();
    private static Map<String, String> sexMap = new HashMap<>();
    private static Map<String, String> wonMap = new HashMap<>();

    public static String getWon(String num) {
        wonMap.put("0", "否");
        wonMap.put("1", "是");
        return wonMap.get(num);
    }

    public static String getSex(String num) {
        sexMap.put("1", "男");
        sexMap.put("2", "女");
        return sexMap.get(num);
    }

    public static String getEducation(String num) {
        eduMap.put("0", "初中");
        eduMap.put("1", "高中");
        eduMap.put("2", "大专");
        eduMap.put("3", "本科");
        eduMap.put("4", "硕士");
        eduMap.put("5", "博士");
        return eduMap.get(num);
    }
}
