package com.jingchengsoft.dzjplatform.common.copy;

import android.view.View;

import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyActivity;


/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 可进行拷贝的副本
 */
public final class CopyActivity extends MyActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_copy;
    }

    @Override
    protected void initView() {
        getWindow().getDecorView().setSystemUiVisibility(getWindow().getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

    }

    @Override
    protected void initData() {

    }
}