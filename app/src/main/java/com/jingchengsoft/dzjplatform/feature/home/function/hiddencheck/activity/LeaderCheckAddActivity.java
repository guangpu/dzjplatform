package com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.activity;

import android.view.View;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ActivityUtils;
import com.hjq.widget.layout.SettingBar;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.utils.HiddenCheckHttpUtils;
import com.jingchengsoft.dzjplatform.http.ApiResponse;
import com.jingchengsoft.dzjplatform.http.CommonException;
import com.jingchengsoft.dzjplatform.http.PretreatmentCallback;

import butterknife.BindView;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  领导带班检查添加
 */
public class LeaderCheckAddActivity extends MyActivity {
    public static void start() {
        ActivityUtils.startActivity(LeaderCheckAddActivity.class);
    }

    private String addId = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_leader_check_add;
    }

    @BindView(R.id.sb_check_input_project_name)
    SettingBar sb_check_input_project_name;
    @BindView(R.id.sb_check_input_project_manager)
    SettingBar sb_check_input_project_manager;
    @BindView(R.id.sb_check_input_create_time)
    SettingBar sb_check_input_create_time;
    @BindView(R.id.sb_check_input_create_id)
    SettingBar sb_check_input_create_id;
    @BindView(R.id.sb_check_input_image_progress)
    SettingBar sb_check_input_image_progress;
    @BindView(R.id.sb_check_input_manage_req)
    SettingBar sb_check_input_manage_req;
    @BindView(R.id.sb_check_input_inspection_date)
    SettingBar sb_check_input_inspection_date;
    @BindView(R.id.sb_check_input_class_leader)
    SettingBar sb_check_input_class_leader;
    @BindView(R.id.sb_check_input_rectificat_date)
    SettingBar sb_check_input_rectificat_date;
    @BindView(R.id.sb_check_input_charge_person)
    SettingBar sb_check_input_charge_person;

    @Override
    protected void initView() {

    }

    @Override
    public void onRightClick(View v) {
        if(addId.equals("")) {
            toast("数据尚未加载完成！");
        } else {
            HiddenCheckQuestionAddActivity.start(addId);
        }
    }

    @Override
    protected void initData() {
        HiddenCheckHttpUtils.getCheckAddId(new PretreatmentCallback<String>() {
            @Override
            public void onResponse(@NonNull ApiResponse response) {
                if(response.getData()!= null) {
                    JSONObject object = JSON.parseObject(response.getData());
                    addId = object.getString("add_id");
                }
            }

            @Override
            public void onException(CommonException e) {

            }
        });
    }


}
