package com.jingchengsoft.dzjplatform.feature.home.function.training.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.hjq.widget.layout.SettingBar;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyActivity;
import com.jingchengsoft.dzjplatform.feature.common.utils.CommonMapUtils;
import com.jingchengsoft.dzjplatform.feature.home.function.specialwork.adapter.SpecialWorkDetailAdapter;
import com.jingchengsoft.dzjplatform.feature.home.function.specialwork.entity.SpecialWorkDetail;
import com.jingchengsoft.dzjplatform.feature.home.function.specialwork.utils.SpecialWorkHttpUtils;
import com.jingchengsoft.dzjplatform.feature.home.function.training.entity.TraineeDetail;
import com.jingchengsoft.dzjplatform.feature.home.function.training.utils.TrainingHttpUtils;
import com.jingchengsoft.dzjplatform.http.ApiResponse;
import com.jingchengsoft.dzjplatform.http.CommonException;
import com.jingchengsoft.dzjplatform.http.PretreatmentCallback;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  入职三级教育培训人员详情
 */
public class TrainingDetailActivity extends MyActivity {
    public static void start(String personId) {
        Bundle bundle = new Bundle();
        bundle.putString("personId", personId);
        ActivityUtils.startActivity(bundle, TrainingDetailActivity.class);
    }

    private String personId = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_trainee_detail;
    }

    @BindView(R.id.sb_trainee_detail_name)
    SettingBar sb_trainee_detail_name;//姓名
    @BindView(R.id.sb_trainee_detail_sex)
    SettingBar sb_trainee_detail_sex;//性别
    @BindView(R.id.sb_trainee_detail_ic_card_num)
    SettingBar sb_trainee_detail_ic_card_num;//身份证号
    @BindView(R.id.sb_trainee_detail_birthday)
    SettingBar sb_trainee_detail_birthday;//出生日期
    @BindView(R.id.sb_trainee_detail_start_date)
    SettingBar sb_trainee_detail_start_date;//入场日期
    @BindView(R.id.sb_trainee_detail_end_date)
    SettingBar sb_trainee_detail_end_date;//退场日期
    @BindView(R.id.sb_trainee_detail_education)
    SettingBar sb_trainee_detail_education;//文化程度
    @BindView(R.id.sb_trainee_detail_job_site)
    SettingBar sb_trainee_detail_job_site;//作业部位
    @BindView(R.id.sb_trainee_detail_special_work)
    SettingBar sb_trainee_detail_special_work;//工种(包含特殊工种)
    @BindView(R.id.sb_trainee_detail_unit)
    SettingBar sb_trainee_detail_unit;//所属单位
    @BindView(R.id.sb_trainee_detail_address)
    SettingBar sb_trainee_detail_address;//家庭住址
    @BindView(R.id.sb_trainee_detail_is_card)
    SettingBar sb_trainee_detail_is_card;//是否有身份证照片
    @BindView(R.id.sb_trainee_detail_is_special_card)
    SettingBar sb_trainee_detail_is_special_card;//是否有特种作业证
    @BindView(R.id.sb_trainee_detail_special_card)
    SettingBar sb_trainee_detail_special_card;//特种作业证编号
    @BindView(R.id.sb_trainee_detail_remark)
    SettingBar sb_trainee_detail_remark;//备注


    @Override
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        personId = bundle.getString("personId");
        LogUtils.i("人员ID:"+personId);
    }

    @Override
    protected void initData() {
        getListData(personId);
    }

    @Override
    protected void initListener() {

    }

    private void getListData(String id) {
        TrainingHttpUtils.getTraineeDetail(id, new PretreatmentCallback<String>() {
            @Override
            public void onResponse(@NonNull ApiResponse response) {
                if(response.getData() != null) {
                    List<TraineeDetail> detailList = JSON.parseArray(response.getData(), TraineeDetail.class);
                    TraineeDetail detail = detailList.get(0);
                    sb_trainee_detail_name.setRightText(detail.getName());
                    sb_trainee_detail_sex.setRightText(CommonMapUtils.getSex(detail.getSex()));
                    sb_trainee_detail_ic_card_num.setRightText(detail.getId_card());
                    sb_trainee_detail_birthday.setRightText(detail.getBirth_date());
                    sb_trainee_detail_start_date.setRightText(detail.getStart_date());
                    sb_trainee_detail_end_date.setRightText(detail.getEnd_date());
                    sb_trainee_detail_education.setRightText(CommonMapUtils.getEducation(detail.getEducation()));
                    sb_trainee_detail_job_site.setRightText(detail.getJob_site());
                    sb_trainee_detail_special_work.setRightText("");
                    sb_trainee_detail_unit.setRightText("");
                    sb_trainee_detail_address.setRightText(detail.getAddress());
                    sb_trainee_detail_is_card.setRightText(CommonMapUtils.getWon(detail.getIs_card()));
                    sb_trainee_detail_is_special_card.setRightText(CommonMapUtils.getWon(detail.getIs_special_work()));
                    sb_trainee_detail_special_card.setRightText(detail.getSpecial_card());
                    sb_trainee_detail_remark.setRightText(detail.getRemark());
                }
            }

            @Override
            public void onException(CommonException e) {

            }
        });
    }
}
