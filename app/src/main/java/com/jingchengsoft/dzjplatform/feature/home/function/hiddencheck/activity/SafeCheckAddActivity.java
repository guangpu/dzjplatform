package com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.activity;

import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.hjq.base.BaseDialog;
import com.hjq.widget.layout.SettingBar;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.InputActivity;
import com.jingchengsoft.dzjplatform.common.MyActivity;
import com.jingchengsoft.dzjplatform.ui.dialog.DateDialog;
import com.jingchengsoft.dzjplatform.ui.dialog.InputBigDialog;
import com.jingchengsoft.dzjplatform.ui.dialog.InputDialog;

import java.util.Calendar;

import butterknife.BindView;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  安全专项检查新增
 */
public class SafeCheckAddActivity extends InputActivity implements View.OnClickListener {
    public static void start() {
        ActivityUtils.startActivity(SafeCheckAddActivity.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_safe_check_add;
    }

    //项目名称
    @BindView(R.id.sb_hidden_input_project_name)
    SettingBar sb_hidden_input_project_name;
    //检查日期
    @BindView(R.id.sb_hidden_input_check_date)
    SettingBar sb_hidden_input_check_date;
    //检查负责人
    @BindView(R.id.sb_hidden_input_check_people)
    SettingBar sb_hidden_input_check_people;
    //参检人员
    @BindView(R.id.sb_hidden_input_check_accompany_people)
    SettingBar sb_hidden_input_check_accompany_people;
    //检查内容
    @BindView(R.id.sb_hidden_input_hidden_details)
    SettingBar sb_hidden_input_hidden_details;
    //整改期限
    @BindView(R.id.sb_hidden_input_rectification_time_limit)
    SettingBar sb_hidden_input_rectification_time_limit;
    //整改负责人
    @BindView(R.id.sb_hidden_input_responsible_people)
    SettingBar sb_hidden_input_responsible_people;

    @Override
    protected void initView() {
        initRequiredField();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initRequiredField() {
        addRequiredField(sb_hidden_input_project_name);
        addRequiredField(sb_hidden_input_check_date);
        addRequiredField(sb_hidden_input_check_people);
        addRequiredField(sb_hidden_input_check_accompany_people);
        addRequiredField(sb_hidden_input_hidden_details);
        addRequiredField(sb_hidden_input_rectification_time_limit);
        addRequiredField(sb_hidden_input_responsible_people);
    }

    @Override
    protected void initListener() {
        sb_hidden_input_project_name.setOnClickListener(this);
        sb_hidden_input_check_date.setOnClickListener(this);
        sb_hidden_input_check_people.setOnClickListener(this);
        sb_hidden_input_check_accompany_people.setOnClickListener(this);
        sb_hidden_input_hidden_details.setOnClickListener(this);
        sb_hidden_input_rectification_time_limit.setOnClickListener(this);
        sb_hidden_input_responsible_people.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sb_hidden_input_project_name:
                new InputDialog.Builder(getActivity())
                        .setTitle("请输入项目名称")
                        .setContent(sb_hidden_input_project_name.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if(StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_hidden_input_project_name.setRightText(content);
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_hidden_input_check_date:
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
                                sb_hidden_input_check_date.setRightText(TimeUtils.millis2String(calendar.getTimeInMillis()).substring(0,10));
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_hidden_input_check_people:
                new InputDialog.Builder(getActivity())
                        .setTitle("请输入检查负责人")
                        .setContent(sb_hidden_input_check_people.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if(StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_hidden_input_check_people.setRightText(content);
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_hidden_input_check_accompany_people:
                new InputDialog.Builder(getActivity())
                        .setTitle("请输入参检人员")
                        .setContent(sb_hidden_input_check_accompany_people.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if(StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_hidden_input_check_accompany_people.setRightText(content);
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_hidden_input_hidden_details:
                new InputBigDialog.Builder(getActivity())
                        .setTitle("请输入检查内容")
                        .setContent(sb_hidden_input_hidden_details.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputBigDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if(StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_hidden_input_hidden_details.setRightText(content);
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_hidden_input_rectification_time_limit:
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
                                sb_hidden_input_rectification_time_limit.setRightText(TimeUtils.millis2String(calendar.getTimeInMillis()).substring(0,10));
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_hidden_input_responsible_people:
                new InputDialog.Builder(getActivity())
                        .setTitle("请输入整改负责人")
                        .setContent(sb_hidden_input_responsible_people.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if(StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_hidden_input_responsible_people.setRightText(content);
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
        }
    }


}
