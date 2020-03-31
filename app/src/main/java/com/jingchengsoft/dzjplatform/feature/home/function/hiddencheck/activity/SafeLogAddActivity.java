package com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.activity;

import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.hjq.base.BaseDialog;
import com.hjq.widget.layout.SettingBar;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyActivity;
import com.jingchengsoft.dzjplatform.ui.dialog.DateDialog;
import com.jingchengsoft.dzjplatform.ui.dialog.InputBigDialog;
import com.jingchengsoft.dzjplatform.ui.dialog.InputDialog;

import java.util.Calendar;

import butterknife.BindView;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  安全监督日志新增
 */
public class SafeLogAddActivity extends MyActivity implements View.OnClickListener {
    public static void start() {
        ActivityUtils.startActivity(SafeLogAddActivity.class);
    }

    private String addId = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_safe_log_add;
    }

    //项目名称
    @BindView(R.id.sb_hidden_input_project_name)
    SettingBar sb_hidden_input_project_name;
    //天气情况
    @BindView(R.id.sb_hidden_input_weather_status)
    SettingBar sb_hidden_input_weather_status;
    //检查日期
    @BindView(R.id.sb_hidden_input_write_date)
    SettingBar sb_hidden_input_write_date;
    //记录人
    @BindView(R.id.sb_hidden_input_write_people)
    SettingBar sb_hidden_input_write_people;
    //安全教育及班前活动情况
    @BindView(R.id.sb_hidden_input_hidden_details)
    SettingBar sb_hidden_input_hidden_details;
    //安全检查情况
    @BindView(R.id.sb_hidden_input_hidden_details1)
    SettingBar sb_hidden_input_hidden_details1;
    //安全隐患及整改情况
    @BindView(R.id.sb_hidden_input_hidden_details2)
    SettingBar sb_hidden_input_hidden_details2;
    //安全技术交底情况
    @BindView(R.id.sb_hidden_input_hidden_details3)
    SettingBar sb_hidden_input_hidden_details3;
    //安全验收情况
    @BindView(R.id.sb_hidden_input_hidden_details4)
    SettingBar sb_hidden_input_hidden_details4;
    //其他
    @BindView(R.id.sb_hidden_input_hidden_details5)
    SettingBar sb_hidden_input_hidden_details5;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRightClick(View v) {
        if (addId.equals("")) {
            toast("数据尚未加载完成！");
        } else {
            HiddenCheckQuestionAddActivity.start(addId);
        }
    }

    @Override
    protected void initListener() {
        sb_hidden_input_project_name.setOnClickListener(this);
        sb_hidden_input_weather_status.setOnClickListener(this);
        sb_hidden_input_write_date.setOnClickListener(this);
        sb_hidden_input_write_people.setOnClickListener(this);
        sb_hidden_input_hidden_details.setOnClickListener(this);
        sb_hidden_input_hidden_details1.setOnClickListener(this);
        sb_hidden_input_hidden_details2.setOnClickListener(this);
        sb_hidden_input_hidden_details3.setOnClickListener(this);
        sb_hidden_input_hidden_details4.setOnClickListener(this);
        sb_hidden_input_hidden_details5.setOnClickListener(this);

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
            case R.id.sb_hidden_input_weather_status:
                new InputDialog.Builder(getActivity())
                        .setTitle("请输入天气情况")
                        .setContent(sb_hidden_input_weather_status.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if(StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_hidden_input_weather_status.setRightText(content);
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_hidden_input_write_date:
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
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_hidden_input_write_people:
                new InputDialog.Builder(getActivity())
                        .setTitle("请输入记录人")
                        .setContent(sb_hidden_input_write_people.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if(StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_hidden_input_write_people.setRightText(content);
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
                        .setTitle("请输入安全教育及班前活动情况")
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
            case R.id.sb_hidden_input_hidden_details1:
                new InputBigDialog.Builder(getActivity())
                        .setTitle("请输入安全检查情况")
                        .setContent(sb_hidden_input_hidden_details1.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputBigDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if(StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_hidden_input_hidden_details1.setRightText(content);
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_hidden_input_hidden_details2:
                new InputBigDialog.Builder(getActivity())
                        .setTitle("请输入安全隐患及整改情况")
                        .setContent(sb_hidden_input_hidden_details2.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputBigDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if(StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_hidden_input_hidden_details2.setRightText(content);
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_hidden_input_hidden_details3:
                new InputBigDialog.Builder(getActivity())
                        .setTitle("请输入安全技术交底情况")
                        .setContent(sb_hidden_input_hidden_details3.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputBigDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if(StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_hidden_input_hidden_details3.setRightText(content);
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_hidden_input_hidden_details4:
                new InputBigDialog.Builder(getActivity())
                        .setTitle("请输入安全验收情况")
                        .setContent(sb_hidden_input_hidden_details4.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputBigDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if(StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_hidden_input_hidden_details4.setRightText(content);
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_hidden_input_hidden_details5:
                new InputBigDialog.Builder(getActivity())
                        .setTitle("请输入其他")
                        .setContent(sb_hidden_input_hidden_details5.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputBigDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if(StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_hidden_input_hidden_details5.setRightText(content);
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
