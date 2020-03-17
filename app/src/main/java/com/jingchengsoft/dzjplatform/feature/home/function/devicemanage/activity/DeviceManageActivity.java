package com.jingchengsoft.dzjplatform.feature.home.function.devicemanage.activity;

import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyActivity;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  设备管理
 */
public class DeviceManageActivity extends MyActivity {
    public static void start() {
        ActivityUtils.startActivity(DeviceManageActivity.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_device_manage;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onRightClick(View v) {
        InspectionAddActivity.start();
    }

    @Override
    protected void initData() {

    }
}
