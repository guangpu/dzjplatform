package com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.hjq.widget.layout.SettingBar;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity.LeaderCheck;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.utils.HiddenCheckHttpUtils;
import com.jingchengsoft.dzjplatform.feature.home.function.specialwork.adapter.SpecialWorkDetailAdapter;
import com.jingchengsoft.dzjplatform.feature.home.function.specialwork.entity.SpecialWorkDetail;
import com.jingchengsoft.dzjplatform.feature.home.function.specialwork.utils.SpecialWorkHttpUtils;
import com.jingchengsoft.dzjplatform.http.ApiResponse;
import com.jingchengsoft.dzjplatform.http.CommonException;
import com.jingchengsoft.dzjplatform.http.PretreatmentCallback;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  领导带班检查详情
 */
public class LeaderCheckDetailActivity extends MyActivity {
    public static void start(String checkId) {
        Bundle bundle = new Bundle();
        bundle.putString("checkId", checkId);
        ActivityUtils.startActivity(bundle, LeaderCheckDetailActivity.class);
    }

    private String checkId = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_leader_check_detail;
    }
    @BindView(R.id.sb_hidden_input_project_name)
    SettingBar sb_hidden_input_project_name;
    @BindView(R.id.sb_hidden_input_project_manager)
    SettingBar sb_hidden_input_project_manager;
    @BindView(R.id.sb_hidden_input_create_time)
    SettingBar sb_hidden_input_create_time;
    @BindView(R.id.sb_hidden_input_create_id)
    SettingBar sb_hidden_input_create_id;
    @BindView(R.id.tv_image_progress)
    AppCompatTextView tv_image_progress;
    @BindView(R.id.tv_manage_req)
    AppCompatTextView tv_manage_req;
    @BindView(R.id.sb_hidden_input_inspection_date)
    SettingBar sb_hidden_input_inspection_date;
    @BindView(R.id.sb_hidden_input_class_leader)
    SettingBar sb_hidden_input_class_leader;
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
        CheckQuestionActivity.start("");
    }

    private void getListData(String checkId) {
        HiddenCheckHttpUtils.getLeaderCheckDetail(checkId, new PretreatmentCallback<String>() {
            @Override
            public void onResponse(@NonNull ApiResponse response) {
                if(response.getData() != null) {
                    LeaderCheck leaderCheck = JSON.parseObject(response.getData(), LeaderCheck.class);
                    sb_hidden_input_project_name.setRightText(leaderCheck.getProject_name());
                    sb_hidden_input_project_manager.setRightText(leaderCheck.getProject_manager());
                    sb_hidden_input_create_time.setRightText(leaderCheck.getCreate_time());
                    sb_hidden_input_create_id.setRightText(leaderCheck.getCreater_id());
                    tv_image_progress.setText(leaderCheck.getImage_progress());
                    tv_manage_req.setText(leaderCheck.getManage_req());
                    sb_hidden_input_inspection_date.setRightText(leaderCheck.getInspection_date());
                    sb_hidden_input_class_leader.setRightText(leaderCheck.getClass_leader());
                    sb_hidden_input_rectificat_date.setTag(leaderCheck.getRectificat_date());
                    sb_hidden_input_charge_person.setRightText(leaderCheck.getCharge_person());
                }
            }

            @Override
            public void onException(CommonException e) {

            }
        });
    }
}
