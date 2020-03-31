package com.jingchengsoft.dzjplatform.feature.home.function.devicemanage.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyActivity;

import java.util.List;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  二维码扫码
 */
public class CodeScanActivity extends MyActivity implements QRCodeView.Delegate, Runnable {
    public static void start() {
        ActivityUtils.startActivity(CodeScanActivity.class);
    }

    public static void start(Activity activity, Intent intent) {
        ActivityUtils.startActivityForResult(activity, intent, 100);
    }

    private ZXingView mZXingView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_code_scan;
    }

    @Override
    protected void initView() {
        mZXingView = findViewById(R.id.zxingview);
        mZXingView.setDelegate(this);

        if(!isHasPermission()) {
            // 申请存储权限
            XXPermissions.with(this)
                    .permission(Permission.CAMERA)
                    .constantRequest()
                    .request(new OnPermission() {
                        @Override
                        public void hasPermission(List<String> granted, boolean isAll) {
                            // 显示加载进度条
                            toast("权限获取成功");
                            new Thread(CodeScanActivity.this).run();
                        }

                        @Override
                        public void noPermission(List<String> denied, boolean quick) {
                            if (quick) {
                                toast(R.string.common_permission_fail);
                                XXPermissions.gotoPermissionSettings(CodeScanActivity.this, false);
                            } else {
                                toast(R.string.common_permission_hint);
                            }
                        }
                    });
        }
    }

    public boolean isHasPermission() {
        if (XXPermissions.isHasPermission(CodeScanActivity.this, Permission.CAMERA)) {
            return true;
        }
        return false;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onStart() {
        super.onStart();

        mZXingView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
//        mZXingView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别

        mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
    }

    @Override
    protected void onStop() {
        mZXingView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mZXingView.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        LogUtils.i("CODE_DATA:", "result:" + result);
        setTitle("扫描结果为：" + result);
        vibrate();

        Intent intent = new Intent();
        intent.putExtra("RESULT", result);
        setResult(RESULT_OK, intent);
        finish();
        //mZXingView.startSpot(); // 开始识别
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }

    @Override
    public void run() {
        super.onStart();

        mZXingView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
//        mZXingView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别

        mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
    }
}
