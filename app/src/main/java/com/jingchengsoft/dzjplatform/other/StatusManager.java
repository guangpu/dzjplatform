package com.jingchengsoft.dzjplatform.other;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.DrawableRes;
import androidx.annotation.RawRes;
import androidx.annotation.RequiresPermission;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;


import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.ui.widget.HintLayout;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/04/18
 *    desc   : 界面状态管理类
 */
public final class StatusManager {

    /** 提示布局 */
    private HintLayout mHintLayout;

    /**
     * 显示加载中
     */
    public void showLoading(View view) {
        showLoading(view, R.raw.loading);
    }

    public void showLoading(View view, @RawRes int id) {
        if (mHintLayout == null) {
            mHintLayout = getHintLayout(view);
        }
        mHintLayout.show();
        mHintLayout.setAnim(id);
        mHintLayout.setHint("");
        mHintLayout.setOnClickListener(null);
    }

    /**
     * 显示加载完成
     */
    public void showComplete() {
        if (mHintLayout != null && mHintLayout.isShow()) {
            mHintLayout.hide();
        }
    }

    /**
     * 显示空提示
     */
    public void showEmpty(View view) {
        showLayout(view, R.drawable.ic_hint_empty, R.string.hint_layout_no_data, null);
    }

    /**
     * 显示错误提示
     */
    public void showError(View view, View.OnClickListener listener) {
        // 判断当前网络是否可用
        if (isNetworkAvailable(view.getContext())) {
            showLayout(view, R.drawable.ic_hint_error, R.string.hint_layout_error_request, listener);
        } else {
            showLayout(view, R.drawable.ic_hint_nerwork, R.string.hint_layout_error_network, listener);
        }
    }

    public void showLayout(View view, @DrawableRes int drawableId, @StringRes int stringId, View.OnClickListener listener) {
        showLayout(view, ContextCompat.getDrawable(view.getContext(), drawableId), view.getResources().getString(stringId), listener);
    }

    /**
     * 显示自定义提示
     */
    public void showLayout(View view, Drawable drawable, CharSequence hint, View.OnClickListener listener) {
        if (mHintLayout == null) {
            mHintLayout = getHintLayout(view);
        }
        mHintLayout.show();
        mHintLayout.setIcon(drawable);
        mHintLayout.setHint(hint);
        mHintLayout.setOnClickListener(listener);
    }

    private HintLayout getHintLayout(View view) {
        HintLayout hintLayout = null;
        if (view instanceof HintLayout) {
            hintLayout = (HintLayout) view;
        } else if (view instanceof ViewGroup) {
            hintLayout = findHintLayout((ViewGroup) view);
        }

        if (hintLayout == null) {
            // 必须在布局中定义一个 HintLayout 控件
            throw new IllegalStateException("are you ok?");
        }
        return hintLayout;
    }

    /**
     * 智能获取布局中的 HintLayout 对象
     */
    private static HintLayout findHintLayout(ViewGroup group) {
        for (int i = 0; i < group.getChildCount(); i++) {
            View view = group.getChildAt(i);
            if ((view instanceof HintLayout)) {
                return (HintLayout) view;
            } else if (view instanceof ViewGroup) {
                HintLayout layout = findHintLayout((ViewGroup) view);
                if (layout != null) {
                    return layout;
                }
            }
        }
        return null;
    }

    /**
     * 判断网络功能是否可用
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    private static boolean isNetworkAvailable(Context context){
        NetworkInfo info = ContextCompat.getSystemService(context, ConnectivityManager.class).getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }
}