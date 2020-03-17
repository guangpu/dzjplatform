package com.jingchengsoft.dzjplatform.feature.home.function.devicemanage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ActivityUtils;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyActivity;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  添加巡检记录
 */
public class InspectionAddActivity extends MyActivity {
    public static void start() {
        ActivityUtils.startActivity(InspectionAddActivity.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inspection_add;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onRightClick(View v) {
        Intent intent = new Intent(this, CodeScanActivity.class);
        CodeScanActivity.start(this, intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(resultCode == RESULT_OK) {
            if(requestCode == 100) {
                toast(intent.getStringExtra("RESULT"));
            }
        }
    }

    @Override
    protected void initData() {

    }
}
