package com.jingchengsoft.dzjplatform.feature.home.function.devicemanage;

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
    protected void initData() {

    }
}
