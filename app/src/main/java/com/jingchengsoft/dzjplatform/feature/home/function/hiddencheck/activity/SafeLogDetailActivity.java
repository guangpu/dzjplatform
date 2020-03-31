package com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.hjq.widget.layout.SettingBar;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyActivity;
import com.jingchengsoft.dzjplatform.feature.common.utils.CommonMapUtils;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity.LeaderCheck;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity.SafeLog;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.utils.HiddenCheckHttpUtils;
import com.jingchengsoft.dzjplatform.http.ApiResponse;
import com.jingchengsoft.dzjplatform.http.CommonException;
import com.jingchengsoft.dzjplatform.http.PretreatmentCallback;

import butterknife.BindView;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  安全监督日志详情
 */
public class SafeLogDetailActivity extends MyActivity {
    public static void start(String checkId) {
        Bundle bundle = new Bundle();
        bundle.putString("checkId", checkId);
        ActivityUtils.startActivity(bundle, SafeLogDetailActivity.class);
    }

    private String checkId = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_safe_log_detail;
    }

    @BindView(R.id.sb_hidden_input_project_name)
    SettingBar sb_hidden_input_project_name;

    @BindView(R.id.sb_hidden_input_weather_condition)
    SettingBar sb_hidden_input_weather_condition;

    @BindView(R.id.sb_hidden_input_create_time)
    SettingBar sb_hidden_input_create_time;

    @BindView(R.id.sb_hidden_input_creater_id)
    SettingBar sb_hidden_input_creater_id;

    @BindView(R.id.sb_hidden_input_waterpower_outage)
    SettingBar sb_hidden_input_waterpower_outage;

    @BindView(R.id.tv_safetyeducation_preclass)
    AppCompatTextView tv_safetyeducation_preclass;

    @BindView(R.id.tv_safety_inspection)
    AppCompatTextView tv_safety_inspection;

    @BindView(R.id.tv_safetyhazards_rectification)
    AppCompatTextView tv_safetyhazards_rectification;

    @BindView(R.id.tv_safetytechnical_disclosure)
    AppCompatTextView tv_safetytechnical_disclosure;

    @BindView(R.id.tv_safety_acceptance)
    AppCompatTextView tv_safety_acceptance;

    @BindView(R.id.tv_other_circumstances)
    AppCompatTextView tv_other_circumstances;

    @Override
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        checkId = bundle.getString("checkId");
        LogUtils.i("检查ID:"+checkId);
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
        CheckQuestionActivity.start(checkId);
    }

    private void getListData(String checkId) {
        HiddenCheckHttpUtils.getCheckDetail(checkId, "4", new PretreatmentCallback<String>() {
            @Override
            public void onResponse(@NonNull ApiResponse response) {
                if(response.getData() != null) {
                    SafeLog safeLog = JSON.parseObject(response.getData(), SafeLog.class);
                    sb_hidden_input_project_name.setRightText(safeLog.getProject_name());
                    sb_hidden_input_weather_condition.setRightText(safeLog.getWeather_condition());
                    sb_hidden_input_create_time.setRightText(safeLog.getCreate_time());
                    sb_hidden_input_creater_id.setRightText(safeLog.getCreater_id());
                    sb_hidden_input_waterpower_outage.setRightText(CommonMapUtils.getWaterPowerCondition(safeLog.getWaterpower_outage()));
                    tv_safetyeducation_preclass.setText(safeLog.getSafety_inspection());
                    tv_safety_inspection.setText(safeLog.getSafety_inspection());
                    tv_safetyhazards_rectification.setText(safeLog.getSafetyhazards_rectification());
                    tv_safetytechnical_disclosure.setText(safeLog.getSafetytechnical_disclosure());
                    tv_safety_acceptance.setText(safeLog.getSafety_acceptance());
                    tv_other_circumstances.setText(safeLog.getOther_circumstances());
                }
            }

            @Override
            public void onException(CommonException e) {

            }
        });
    }
}
