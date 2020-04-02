package com.jingchengsoft.dzjplatform.feature.home.function.riskmanage.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.hjq.widget.layout.SettingBar;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.utils.HiddenCheckHttpUtils;
import com.jingchengsoft.dzjplatform.feature.home.function.riskmanage.entity.Risk;
import com.jingchengsoft.dzjplatform.http.ApiResponse;
import com.jingchengsoft.dzjplatform.http.CommonException;
import com.jingchengsoft.dzjplatform.http.PretreatmentCallback;

import butterknife.BindView;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  重大危险源详情
 */
public class RiskManageDetailActivity extends MyActivity {
    public static void start(String checkId, Risk risk) {
        Bundle bundle = new Bundle();
        bundle.putString("checkId", checkId);
        bundle.putSerializable("risk", risk);
        ActivityUtils.startActivity(bundle, RiskManageDetailActivity.class);
    }

    private String checkId = "";
    private Risk risk;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_risk_manage_detail;
    }

    @BindView(R.id.sb_check_input_comp_id)
    SettingBar sb_check_input_comp_id;
    @BindView(R.id.tv_danger_rs_desc)
    AppCompatTextView tv_danger_rs_desc;
    @BindView(R.id.sb_check_input_plan_start)
    SettingBar sb_check_input_plan_start;
    @BindView(R.id.sb_check_input_plan_end)
    SettingBar sb_check_input_plan_end;
    @BindView(R.id.sb_check_input_actual_start)
    SettingBar sb_check_input_actual_start;
    @BindView(R.id.sb_check_input_actual_end)
    SettingBar sb_check_input_actual_end;

    @Override
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        checkId = bundle.getString("checkId");
        risk = (Risk) bundle.getSerializable("risk");
        LogUtils.i("检查ID:" + checkId);
    }

    @Override
    protected void initData() {
        //getListData(checkId);
        sb_check_input_comp_id.setRightText(risk.getComp_id());
        tv_danger_rs_desc.setText(risk.getDanger_rs_des());
        sb_check_input_plan_start.setRightText(risk.getPlan_start());
        sb_check_input_plan_end.setRightText(risk.getPlan_end());
        sb_check_input_actual_start.setRightText(risk.getActual_start());
        sb_check_input_actual_end.setRightText(risk.getActual_end());
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
