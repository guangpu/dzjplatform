package com.jingchengsoft.dzjplatform.http;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.LogUtils;
import com.zhouyou.http.callback.CallBack;
import com.zhouyou.http.exception.ApiException;

/**
 * @author MaybeSix
 * @date 2019/9/29
 * @desc TODO.
 */
public abstract class PretreatmentCallback<String> extends CallBack {
    @Override
    public void onSuccess(@NonNull Object o) {
        try {
            LogUtils.d(this.getClass().getName() + ",请求结果：" + o.toString());
            ApiResponse response = JSON.parseObject(o.toString(), ApiResponse.class);
            onResponse(response);
        } catch (Exception e) {
            onException(new CommonException(CommonException.EXCEPTION, e));
        } finally {
            onFinally();
        }
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onCompleted() {
    }

    public abstract void onResponse(@NonNull ApiResponse response);

    @Override
    public void onError(ApiException e) {
        onException(new CommonException(CommonException.API_EXCEPTION, e));
    }

    public abstract void onException(CommonException e);

    public void onFinally() {

    }

}
