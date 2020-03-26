package com.jingchengsoft.dzjplatform.feature.home.function.devicemanage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.hjq.base.BaseDialog;
import com.hjq.widget.layout.SettingBar;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.callback.SelectCallback;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.InputActivity;
import com.jingchengsoft.dzjplatform.common.MyActivity;
import com.jingchengsoft.dzjplatform.feature.common.activity.ImageActivity;
import com.jingchengsoft.dzjplatform.feature.common.adapter.PhotoAdapter;
import com.jingchengsoft.dzjplatform.other.GlideEngine;
import com.jingchengsoft.dzjplatform.ui.dialog.InputBigDialog;
import com.jingchengsoft.dzjplatform.ui.dialog.InputDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  添加巡检记录
 */
public class InspectionAddActivity extends InputActivity {
    public static void start() {
        ActivityUtils.startActivity(InspectionAddActivity.class);
    }

    private PhotoAdapter photoAdapter;
    private ArrayList<Photo> imageList = new ArrayList<>();
    private List<File> fileList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inspection_add;
    }

    @BindView(R.id.sb_device_inspection_input_code)
    SettingBar sb_device_inspection_input_code;
    @BindView(R.id.sb_device_inspection_input_name)
    SettingBar sb_device_inspection_input_name;
    @BindView(R.id.sb_device_inspection_input_inspection_content)
    SettingBar sb_device_inspection_input_inspection_content;
    @BindView(R.id.sb_device_inspection_input_abnormal)
    SettingBar sb_device_inspection_input_abnormal;
    @BindView(R.id.rg_status)
    RadioGroup rg_status;
    @BindView(R.id.gv_question_input)
    GridView gv_question_input;


    @Override
    protected void initView() {
        initRequiredField();
        photoAdapter = new PhotoAdapter(imageList, this, true);
        gv_question_input.setAdapter(photoAdapter);
    }

    @Override
    public void onRightClick(View v) {
        Intent intent = new Intent(this, CodeScanActivity.class);
        CodeScanActivity.start(this, intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(resultCode == RESULT_OK) {
            if(requestCode == 100) {
                toast(intent.getStringExtra("RESULT"));
            }
        }
    }

    @Override
    protected void initListener() {
        sb_device_inspection_input_code.setOnClickListener(this);
        sb_device_inspection_input_name.setOnClickListener(this);
        sb_device_inspection_input_inspection_content.setOnClickListener(this);
        sb_device_inspection_input_abnormal.setOnClickListener(this);
        rg_status.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });
        gv_question_input.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectImage(position);
            }
        });
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
    protected void initData() {

    }

    @Override
    protected void initRequiredField() {
        addRequiredField(sb_device_inspection_input_code);
        addRequiredField(sb_device_inspection_input_name);
        addRequiredField(sb_device_inspection_input_inspection_content);
        addRequiredField(sb_device_inspection_input_abnormal);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sb_device_inspection_input_code:
                new InputDialog.Builder(getActivity())
                        .setTitle("请输入设备编号")
                        .setContent(sb_device_inspection_input_code.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_device_inspection_input_code.setRightText(content);
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_device_inspection_input_name:
                new InputDialog.Builder(getActivity())
                        .setTitle("请输入设备名称")
                        .setContent(sb_device_inspection_input_name.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_device_inspection_input_name.setRightText(content);
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_device_inspection_input_inspection_content:
                new InputBigDialog.Builder(getActivity())
                        .setTitle("请输入检查内容")
                        .setContent(sb_device_inspection_input_inspection_content.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputBigDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_device_inspection_input_inspection_content.setRightText(content);
                                }
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {

                            }
                        })
                        .show();
                break;
            case R.id.sb_device_inspection_input_abnormal:
                new InputBigDialog.Builder(getActivity())
                        .setTitle("请输入异常情况说明")
                        .setContent(sb_device_inspection_input_abnormal.getRightText().toString())
                        .setConfirm("确定")
                        .setCancel("取消")
                        //.setHint("")
                        .setListener(new InputBigDialog.OnListener() {
                            @Override
                            public void onConfirm(BaseDialog dialog, String content) {
                                if (StringUtils.isEmpty(content)) {
                                    toast("不能为空");
                                } else {
                                    sb_device_inspection_input_abnormal.setRightText(content);
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
