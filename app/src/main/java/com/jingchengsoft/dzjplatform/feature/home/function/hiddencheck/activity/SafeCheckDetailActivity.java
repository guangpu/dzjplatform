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
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity.LeaderCheck;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity.SafeCheck;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.utils.HiddenCheckHttpUtils;
import com.jingchengsoft.dzjplatform.http.ApiResponse;
import com.jingchengsoft.dzjplatform.http.CommonException;
import com.jingchengsoft.dzjplatform.http.PretreatmentCallback;

import butterknife.BindView;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  安全专项检查详情
 */
public class SafeCheckDetailActivity extends MyActivity {
    public static void start(String checkId) {
        Bundle bundle = new Bundle();
        bundle.putString("checkId", checkId);
        ActivityUtils.startActivity(bundle, SafeCheckDetailActivity.class);
    }

    private String checkId = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_safe_check_detail;
    }

    @BindView(R.id.sb_hidden_input_project_name)
    SettingBar sb_hidden_input_project_name;
    @BindView(R.id.sb_hidden_input_inspection_date)
    SettingBar sb_hidden_input_inspection_date;
    @BindView(R.id.sb_hidden_input_inspection_person)
    SettingBar sb_hidden_input_inspection_person;
    @BindView(R.id.sb_hidden_input_check_join_persons)
    SettingBar sb_hidden_input_check_join_persons;
    @BindView(R.id.tv_inspection_contents)
    AppCompatTextView tv_inspection_contents;
    @BindView(R.id.sb_hidden_input_rectificat_date)
    SettingBar sb_hidden_input_rectificat_date;
    @BindView(R.id.sb_hidden_input_charge_person)
    SettingBar sb_hidden_input_charge_person;

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
        HiddenCheckHttpUtils.getCheckDetail(checkId, "2", new PretreatmentCallback<String>() {
            @Override
            public void onResponse(@NonNull ApiResponse response) {
                if(response.getData() != null) {
                    SafeCheck safeCheck = JSON.parseObject(response.getData(), SafeCheck.class);
                    sb_hidden_input_project_name.setRightText(safeCheck.getProject_name());
                    sb_hidden_input_inspection_date.setRightText(safeCheck.getInspection_date());
                    sb_hidden_input_inspection_person.setRightText(safeCheck.getInspection_person());
                    sb_hidden_input_check_join_persons.setRightText(safeCheck.getJoin_persons());
                    tv_inspection_contents.setText(safeCheck.getInspection_contents());
                    sb_hidden_input_rectificat_date.setRightText(safeCheck.getRectificat_date());
                    sb_hidden_input_charge_person.setRightText(safeCheck.getCharge_person());
                }
            }

            @Override
            public void onException(CommonException e) {

            }
        });
    }
}
