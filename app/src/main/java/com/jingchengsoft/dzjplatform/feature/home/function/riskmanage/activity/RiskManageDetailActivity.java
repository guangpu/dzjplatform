package com.jingchengsoft.dzjplatform.feature.home.function.riskmanage.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.utils.HiddenCheckHttpUtils;
import com.jingchengsoft.dzjplatform.http.ApiResponse;
import com.jingchengsoft.dzjplatform.http.CommonException;
import com.jingchengsoft.dzjplatform.http.PretreatmentCallback;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  重大危险源详情
 */
public class RiskManageDetailActivity extends MyActivity {
    public static void start(String checkId) {
        Bundle bundle = new Bundle();
        bundle.putString("checkId", checkId);
        ActivityUtils.startActivity(bundle, RiskManageDetailActivity.class);
    }

    private String checkId = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_risk_manage_detail;
    }

    @Override
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        checkId = bundle.getString("checkId");
        LogUtils.i("检查ID:" + checkId);
    }

    @Override
    protected void initData() {
        getListData(checkId);
    }


    @Override
    protected void initListener() {

    }

    @Override
    public void onRightClick(View v) {

    }

    private void getListData(String checkId) {
        HiddenCheckHttpUtils.getCheckDetail(checkId, "",new PretreatmentCallback<String>() {
            @Override
            public void onResponse(@NonNull ApiResponse response) {
                if (response.getData() != null) {

                }
            }

            @Override
            public void onException(CommonException e) {

            }
        });
    }
}
