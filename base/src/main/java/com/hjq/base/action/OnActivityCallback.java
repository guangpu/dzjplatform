package com.hjq.base.action;

import android.content.Intent;

import androidx.annotation.Nullable;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/09/15
 *    desc   : Activity 回调接口
 */
public interface OnActivityCallback {

    /**
     * 结果回调
     *
     * @param resultCode        结果码
     * @param data              数据
     */
    void onActivityResult(int resultCode, @Nullable Intent data);
}