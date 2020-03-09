package com.jingchengsoft.dzjplatform.common;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import androidx.annotation.StringRes;

import com.gyf.immersionbar.ImmersionBar;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.hjq.base.BaseFragment;
import com.hjq.toast.ToastUtils;
import com.jingchengsoft.dzjplatform.other.AppConfig;
import com.jingchengsoft.dzjplatform.other.StatusManager;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 项目中 Fragment 懒加载基类
 */
public abstract class MyFragment<A extends MyActivity> extends BaseFragment<A>
        implements OnTitleBarListener {

    /** 标题栏对象 */
    private TitleBar mTitleBar;
    /** 状态栏沉浸 */
    private ImmersionBar mImmersionBar;

    /**
     * 获取标题栏 id
     */
    protected int getTitleId() {
        return 0;
    }

    @Override
    protected void initFragment() {
//        ButterKnife.bind(this, getView());

        if (getTitleId() > 0) {
            // 勤快模式
            View view = findViewById(getTitleId());
            if (view instanceof TitleBar) {
                mTitleBar = (TitleBar) view;
            }
        } else if (getTitleId() == 0 && getView() instanceof ViewGroup) {
            // 懒人模式
            mTitleBar = MyActivity.findTitleBar((ViewGroup) getView());
        }
        if (mTitleBar != null) {
            mTitleBar.setOnTitleBarListener(this);
        }

        initImmersion();
        super.initFragment();
    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersion() {

        // 初始化沉浸式状态栏
        if (isStatusBarEnabled()) {
            statusBarConfig().init();

            // 设置标题栏沉浸
            if (getTitleId() > 0) {
                ImmersionBar.setTitleBar(this, findViewById(getTitleId()));
            } else if (mTitleBar != null) {
                ImmersionBar.setTitleBar(this, mTitleBar);
            }
        }
    }

    /**
     * 是否在Fragment使用沉浸式
     */
    public boolean isStatusBarEnabled() {
        return true;
    }

    /**
     * 获取状态栏沉浸的配置对象
     */
    protected ImmersionBar getStatusBarConfig() {
        return mImmersionBar;
    }

    /**
     * 初始化沉浸式
     */
    private ImmersionBar statusBarConfig() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this)
                // 默认状态栏字体颜色为黑色
                .statusBarDarkFont(statusBarDarkFont())
                // 解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
                .keyboardEnable(true);
        return mImmersionBar;
    }

    /**
     * 获取状态栏字体颜色
     */
    protected boolean statusBarDarkFont() {
        // 返回真表示黑色字体
        return false;
    }

    /**
     * 设置标题栏的标题
     */
    public void setTitle(@StringRes int id) {
        setTitle(getString(id));
    }

    /**
     * 设置标题栏的标题
     */
    public void setTitle(CharSequence title) {
        if (mTitleBar != null) {
            mTitleBar.setTitle(title);
        } else {
            // 如果没有标题栏对象就直接设置给绑定的 Activity
            getAttachActivity().setTitle(title);
        }
    }

    @Nullable
    public TitleBar getTitleBar() {
        if (getTitleId() > 0 && findViewById(getTitleId()) instanceof TitleBar) {
            return findViewById(getTitleId());
        }
        return null;
    }

    /**
     * 显示吐司
     */
    public void toast(CharSequence text) {
        ToastUtils.show(text);
    }

    public void toast(@StringRes int id) {
        ToastUtils.show(id);
    }

    public void toast(Object object) {
        ToastUtils.show(object);
    }

    /**
     * 打印日志
     */
    public void log(Object object) {
        if (AppConfig.isDebug()) {
            Log.v(getClass().getSimpleName(), object != null ? object.toString() : "null");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // 重新初始化状态栏
        statusBarConfig().init();
//        UmengClient.onResume(this);
    }

    @Override
    public void onPause() {
//        UmengClient.onPause(this);
        super.onPause();
    }

    /**
     * {@link OnTitleBarListener}
     */

    /**
     * TitleBar 左边的View被点击了
     */
    @Override
    public void onLeftClick(View v) {}

    /**
     * TitleBar 中间的View被点击了
     */
    @Override
    public void onTitleClick(View v) {}

    /**
     * TitleBar 右边的View被点击了
     */
    @Override
    public void onRightClick(View v) {}

    private final StatusManager mStatusManager = new StatusManager();

    /**
     * 显示加载中
     */
    public void showLoading() {
        mStatusManager.showLoading(getView());
    }

    public void showLoading(@RawRes int id) {
        mStatusManager.showLoading(getView(), id);
    }

    /**
     * 显示加载完成
     */
    public void showComplete() {
        mStatusManager.showComplete();
    }

    /**
     * 显示空提示
     */
    public void showEmpty() {
        mStatusManager.showEmpty(getView());
    }

    /**
     * 显示错误提示
     */
    public void showError(View.OnClickListener listener) {
        mStatusManager.showError(getView(), listener);
    }

    /**
     * 显示自定义提示
     */
    public void showLayout(@DrawableRes int drawableId, @StringRes int stringId, View.OnClickListener listener) {
        mStatusManager.showLayout(getView(), drawableId, stringId, listener);
    }

    public void showLayout(Drawable drawable, CharSequence hint, View.OnClickListener listener) {
        mStatusManager.showLayout(getView(), drawable, hint, listener);
    }
}