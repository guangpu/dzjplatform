package com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.hjq.base.BaseDialog;
import com.hjq.widget.layout.SettingBar;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.callback.SelectCallback;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyActivity;
import com.jingchengsoft.dzjplatform.feature.common.activity.ImageActivity;
import com.jingchengsoft.dzjplatform.feature.common.adapter.PhotoAdapter;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.utils.HiddenCheckHttpUtils;
import com.jingchengsoft.dzjplatform.http.ApiResponse;
import com.jingchengsoft.dzjplatform.http.CommonException;
import com.jingchengsoft.dzjplatform.http.PretreatmentCallback;
import com.jingchengsoft.dzjplatform.other.GlideEngine;
import com.jingchengsoft.dzjplatform.ui.dialog.DateDialog;
import com.jingchengsoft.dzjplatform.ui.dialog.InputBigDialog;
import com.jingchengsoft.dzjplatform.ui.dialog.InputDialog;
import com.zhouyou.http.body.ProgressResponseCallBack;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

/**
 * author : wgp
 * time   :  2020/3/12
 * desc   :  检查问题添加
 */
public class HiddenCheckQuestionAddActivity extends MyActivity implements View.OnClickListener {

    public static void start(String addId) {
        Bundle bundle = new Bundle();
        bundle.putString("addId", addId);
        ActivityUtils.startActivity(bundle, HiddenCheckQuestionAddActivity.class);
    }

    private PhotoAdapter photoAdapter;
    private ArrayList<Photo> imageList = new ArrayList<>();
    private List<File> fileList = new ArrayList<>();

    private String projectId, projectName, problemDesc, rectificationReq, rectificationPerson, completeDate, remark, inspectionType, addId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hidden_check_question_add;
    }

    //问题描述
    @BindView(R.id.sb_question_input_description)
    SettingBar sb_question_input_description;
    //整改措施
    @BindView(R.id.sb_question_input_rectification_solutions)
    SettingBar sb_question_input_rectification_solutions;
    //整改人
    @BindView(R.id.sb_question_input_rectification_people)
    SettingBar sb_question_input_rectification_people;
    //完成期限
    @BindView(R.id.sb_question_input_finish_date)
    SettingBar sb_question_input_finish_date;
    //备注
    @BindView(R.id.sb_question_input_remark)
    SettingBar sb_question_input_remark;
    //现场照片
    @BindView(R.id.gv_question_input)
    GridView gv_question_input;
    @BindView(R.id.btn_hidden_input_sure)
    AppCompatButton btn_hidden_input_sure;

    @Override
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        addId = bundle.getString("addId");
        projectId = "12340";
        projectName = "测试项目";

        photoAdapter = new PhotoAdapter(imageList, this, true);
        gv_question_input.setAdapter(photoAdapter);
    }

    @Override
    protected void initListener() {
        sb_question_input_description.setOnClickListener(this);
        sb_question_input_rectification_solutions.setOnClickListener(this);
        sb_question_input_rectification_people.setOnClickListener(this);
        sb_question_input_finish_date.setOnClickListener(this);
        sb_question_input_remark.setOnClickListener(this);
        btn_hidden_input_sure.setOnClickListener(this);
        gv_question_input.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectImage(position);
            }
        });
        gv_question_input.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (position < imageList.size()) {
                    imageList.remove(position);
                    toast("附件已经移除！");
                    photoAdapter.notifyDataSetChanged();
                }
                return true;
            }
        });

    }

    @Override
    protected void initData() {

    }

    //显示添加录像或者取证照片
    private void selectImage(int position) {
        if (position == imageList.size()) {
            if (position == 5) {
                toast("附件已达最大选择数量，请选择删除");
            } else {
                addPhotos();
            }
        } else {
            ImageActivity.start(this, imageList.get(position).path);
        }
    }

    private void addPhotos() {
        //附件选择
        EasyPhotos.createAlbum(this, true, GlideEngine.getInstance())
                .setPuzzleMenu(false)
                .setFileProviderAuthority("com.jingchengsoft.dzjplatform.provider")
                .setCount(5 - imageList.size())
                .start(new SelectCallback() {
                    @Override
                    public void onResult(ArrayList<Photo> photos, boolean isOriginal) {
                        LogUtils.i("选中了图片数目：" + photos.size());
                        for (Photo photo : photos) {
                            imageList.add(photo);
                            fileList.add(new File(photo.path));
                        }
                        photoAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sb_question_input_description:
                new InputBigDialog.Builder(getActivity())
                        .setTitle("请输入问题描述")
                        .setContent(sb_question_input_description.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputBigDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_question_input_description.setRightText(content);
                                    problemDesc = content;
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_question_input_rectification_solutions:
                new InputBigDialog.Builder(getActivity())
                        .setTitle("请输入整改措施")
                        .setContent(sb_question_input_rectification_solutions.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputBigDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_question_input_rectification_solutions.setRightText(content);
                                    rectificationReq = content;
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_question_input_rectification_people:
                new InputDialog.Builder(getActivity())
                        .setTitle("请输入整改人")
                        .setContent(sb_question_input_rectification_people.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_question_input_rectification_people.setRightText(content);
                                    rectificationPerson = content;
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_question_input_finish_date:
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
                                sb_question_input_finish_date.setRightText(TimeUtils.millis2String(calendar.getTimeInMillis()).substring(0, 10));
                                completeDate = TimeUtils.millis2String(calendar.getTimeInMillis()).substring(0, 10);
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_question_input_remark:
                new InputDialog.Builder(getActivity())
                        .setTitle("请输入备注")
                        .setContent(sb_question_input_remark.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_question_input_remark.setRightText(content);
                                    remark = content;
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.btn_hidden_input_sure:
                addQuestion();
                break;
        }
    }

    private void addQuestion() {
        HiddenCheckHttpUtils.addCheckQuestion(projectId, projectName, problemDesc, rectificationReq, rectificationPerson, completeDate, remark, "1", addId, fileList, new ProgressResponseCallBack() {
            @Override
            public void onResponseProgress(long bytesWritten, long contentLength, boolean done) {

            }
        }, new PretreatmentCallback<String>() {
            @Override
            public void onResponse(@NonNull ApiResponse response) {
                if (response.isOk()) {
                    toast("添加成功");
                    finish();
                }
            }

            @Override
            public void onException(CommonException e) {
                toast("添加失败");
            }
        });
    }

}
