package com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.activity;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.hjq.base.BaseDialog;
import com.hjq.widget.layout.SettingBar;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.InputActivity;
import com.jingchengsoft.dzjplatform.common.MyActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.utils.HiddenCheckHttpUtils;
import com.jingchengsoft.dzjplatform.http.ApiResponse;
import com.jingchengsoft.dzjplatform.http.CommonException;
import com.jingchengsoft.dzjplatform.http.PretreatmentCallback;
import com.jingchengsoft.dzjplatform.ui.dialog.DateDialog;
import com.jingchengsoft.dzjplatform.ui.dialog.InputBigDialog;
import com.jingchengsoft.dzjplatform.ui.dialog.InputDialog;
import com.zhouyou.http.body.ProgressResponseCallBack;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  领导带班检查添加
 */
public class LeaderCheckAddActivity extends InputActivity {
    public static void start() {
        ActivityUtils.startActivity(LeaderCheckAddActivity.class);
    }

    private String addId = "";
    private String projectName, projectManager, createTime, createId, imageProgress, manageReq, inspectionDate, classLeader, rectificatDate, chargePerson;

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
    @BindView(R.id.btn_check_input_sure)
    AppCompatButton btn_check_input_sure;

    @Override
    protected void initView() {
        initRequiredField();
    }

    //添加必填项
    @Override
    protected void initRequiredField() {
        addRequiredField(sb_check_input_project_name);
        addRequiredField(sb_check_input_project_manager);
        addRequiredField(sb_check_input_create_time);
        addRequiredField(sb_check_input_create_id);
        addRequiredField(sb_check_input_image_progress);
        addRequiredField(sb_check_input_manage_req);
        addRequiredField(sb_check_input_inspection_date);
        addRequiredField(sb_check_input_class_leader);
        addRequiredField(sb_check_input_rectificat_date);
        addRequiredField(sb_check_input_charge_person);
    }

    @Override
    protected void initListener() {
        sb_check_input_project_name.setOnClickListener(this);
        sb_check_input_project_manager.setOnClickListener(this);
        sb_check_input_create_time.setOnClickListener(this);
        sb_check_input_create_id.setOnClickListener(this);
        sb_check_input_image_progress.setOnClickListener(this);
        sb_check_input_manage_req.setOnClickListener(this);
        sb_check_input_inspection_date.setOnClickListener(this);
        sb_check_input_class_leader.setOnClickListener(this);
        sb_check_input_rectificat_date.setOnClickListener(this);
        sb_check_input_charge_person.setOnClickListener(this);
        sb_check_input_project_name.setOnClickListener(this);
        btn_check_input_sure.setOnClickListener(this);
    }

    @Override
    public void onRightClick(View v) {
        if (addId.equals("")) {
            toast("数据尚未加载完成！");
        } else {
            HiddenCheckQuestionAddActivity.start(addId);
        }
    }

    private void addLeaderCheck() {
        HiddenCheckHttpUtils.addLeaderCheck("12340", projectName, projectManager, createTime, createId, imageProgress, manageReq, inspectionDate, classLeader, rectificatDate, chargePerson, addId,
        new ProgressResponseCallBack() {
            @Override
            public void onResponseProgress(long bytesWritten, long contentLength, boolean done) {

            }
        },
        new PretreatmentCallback<String>() {
            @Override
            public void onResponse(@NonNull ApiResponse response) {
                if(response.isOk()) {
                    toast("添加成功");
                } else {
                    toast(response.getMsg());
                }
            }

            @Override
            public void onException(CommonException e) {
                toast(e.getException().getMessage());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sb_check_input_project_name:
                new InputDialog.Builder(getActivity())
                        .setTitle("请输入项目名称")
                        .setContent(sb_check_input_project_name.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_check_input_project_name.setRightText(content);
                                    projectName = content;
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_check_input_project_manager:
                new InputDialog.Builder(getActivity())
                        .setTitle("请输入项目经理")
                        .setContent(sb_check_input_project_manager.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_check_input_project_manager.setRightText(content);
                                    projectManager = content;
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_check_input_create_time:
                new DateDialog.Builder(getActivity())
                        .setTitle("请选择日期")
                        .setConfirm("确定")
                        .setCancel("取消")
                        .setDate(0)
                        .setListener(new DateDialog.OnListener() {
                            @Override
                            public void onSelected(BaseDialog dialog, int year, int month, int day) {
                                toast(year + getString(R.string.common_year) + month + getString(R.string.common_month) + day + getString(R.string.common_day));
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, month - 1);
                                calendar.set(Calendar.DAY_OF_MONTH, day);
                                calendar.set(Calendar.HOUR, 0);
                                calendar.set(Calendar.MINUTE, 0);
                                calendar.set(Calendar.SECOND, 0);
                                calendar.set(Calendar.MILLISECOND, 0);
                                sb_check_input_create_time.setRightText(TimeUtils.millis2String(calendar.getTimeInMillis()).substring(0, 10));
                                createTime = TimeUtils.millis2String(calendar.getTimeInMillis()).substring(0, 10);
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_check_input_create_id:
                new InputDialog.Builder(getActivity())
                        .setTitle("请输入填报人")
                        .setContent(sb_check_input_create_id.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_check_input_create_id.setRightText(content);
                                    createId = content;
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_check_input_image_progress:
                new InputBigDialog.Builder(getActivity())
                        .setTitle("请输入形象进度")
                        .setContent(sb_check_input_image_progress.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputBigDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_check_input_image_progress.setRightText(content);
                                    imageProgress = content;
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_check_input_manage_req:
                new InputBigDialog.Builder(getActivity())
                        .setTitle("请输入管理要求")
                        .setContent(sb_check_input_manage_req.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputBigDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_check_input_manage_req.setRightText(content);
                                    manageReq = content;
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_check_input_inspection_date:
                new DateDialog.Builder(getActivity())
                        .setTitle("请选择日期")
                        .setConfirm("确定")
                        .setCancel("取消")
                        .setDate(0)
                        .setListener(new DateDialog.OnListener() {
                            @Override
                            public void onSelected(BaseDialog dialog, int year, int month, int day) {
                                toast(year + getString(R.string.common_year) + month + getString(R.string.common_month) + day + getString(R.string.common_day));
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, month - 1);
                                calendar.set(Calendar.DAY_OF_MONTH, day);
                                calendar.set(Calendar.HOUR, 0);
                                calendar.set(Calendar.MINUTE, 0);
                                calendar.set(Calendar.SECOND, 0);
                                calendar.set(Calendar.MILLISECOND, 0);
                                sb_check_input_inspection_date.setRightText(TimeUtils.millis2String(calendar.getTimeInMillis()).substring(0, 10));
                                inspectionDate = TimeUtils.millis2String(calendar.getTimeInMillis()).substring(0, 10);
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_check_input_class_leader:
                new InputDialog.Builder(getActivity())
                        .setTitle("请输入带班领导")
                        .setContent(sb_check_input_class_leader.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_check_input_class_leader.setRightText(content);
                                    classLeader = content;
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_check_input_rectificat_date:
                new DateDialog.Builder(getActivity())
                        .setTitle("请选择日期")
                        .setConfirm("确定")
                        .setCancel("取消")
                        .setDate(0)
                        .setListener(new DateDialog.OnListener() {
                            @Override
                            public void onSelected(BaseDialog dialog, int year, int month, int day) {
                                toast(year + getString(R.string.common_year) + month + getString(R.string.common_month) + day + getString(R.string.common_day));
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, month - 1);
                                calendar.set(Calendar.DAY_OF_MONTH, day);
                                calendar.set(Calendar.HOUR, 0);
                                calendar.set(Calendar.MINUTE, 0);
                                calendar.set(Calendar.SECOND, 0);
                                calendar.set(Calendar.MILLISECOND, 0);
                                sb_check_input_rectificat_date.setRightText(TimeUtils.millis2String(calendar.getTimeInMillis()).substring(0, 10));
                                rectificatDate = TimeUtils.millis2String(calendar.getTimeInMillis()).substring(0, 10);
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_check_input_charge_person:
                new InputDialog.Builder(getActivity())
                        .setTitle("请输入带班领导")
                        .setContent(sb_check_input_charge_person.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_check_input_charge_person.setRightText(content);
                                    chargePerson = content;
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.btn_check_input_sure:
                if(checkAllRequiredField(requiredFieldList)) {
                    addLeaderCheck();
                }
                break;
        }
    }

    @Override
    protected void initData() {
        showLoading();
        HiddenCheckHttpUtils.getCheckAddId(new PretreatmentCallback<String>() {
            @Override
            public void onResponse(@NonNull ApiResponse response) {
                showComplete();
                if (response.getData() != null) {
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
