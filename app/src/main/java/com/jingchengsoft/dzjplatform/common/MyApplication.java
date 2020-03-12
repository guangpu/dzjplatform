package com.jingchengsoft.dzjplatform.common;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import androidx.multidex.MultiDex;

import com.hjq.bar.TitleBar;
import com.hjq.bar.style.TitleBarLightStyle;
import com.hjq.image.ImageLoader;
import com.hjq.toast.ToastInterceptor;
import com.hjq.toast.ToastUtils;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.http.HttpConfig;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.zhouyou.http.EasyHttp;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 项目中的 Application 基类
 */
public final class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initSDK(this);
        LiveEventBus.
                config().
                supportBroadcast(this).
                lifecycleObserverAlwaysActive(true).
                autoClear(false);
    }

    /**
     * 初始化一些第三方框架
     */
    public static void initSDK(Application application) {
        // 友盟统计、登录、分享 SDK
//        UmengClient.init(application);

        // 吐司工具类
        ToastUtils.init(application);

        // 设置 Toast 拦截器
        ToastUtils.setToastInterceptor(new ToastInterceptor() {
            @Override
            public boolean intercept(Toast toast, CharSequence text) {
                boolean intercept = super.intercept(toast, text);
                if (intercept) {
                    Log.e("Toast", "空 Toast");
                } else {
                    Log.i("Toast", text.toString());
                }
                return intercept;
            }
        });

        // 标题栏全局样式
        TitleBar.initStyle(new TitleBarLightStyle(application) {

            @Override
            public Drawable getBackground() {
                return new ColorDrawable(getColor(R.color.colorPrimary));
            }

//            @Override
//            public Drawable getBackIcon() {
//                return getDrawable(R.drawable.ic_back_black);
//            }
        });

        // 图片加载器
        ImageLoader.init(application);

        // Bugly 异常捕捉
        //CrashReport.initCrashReport(application, BuildConfig.BUGLY_ID, true);

        // Crash 捕捉界面
//        CaocConfig.Builder.create()
//                .backgroundMode(CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM)
//                .enabled(true)
//                .trackActivities(true)
//                .minTimeBetweenCrashesMs(2000)
//                // 重启的 Activity
//                .restartActivity(HomeActivity.class)
//                // 错误的 Activity
//                .errorActivity(CrashActivity.class)
//                // 设置监听器
//                //.eventListener(new YourCustomEventListener())
//                .apply();

//         设置全局的 Header 构建器
//        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> new ClassicsHeader(context).setEnableLastTime(false));
//         设置全局的 Footer 构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> new ClassicsFooter(context).setDrawableSize(20));
        //EasyHttp全局配置
        EasyHttp.init(application);
        EasyHttp.getInstance()
                .debug("EasyHttp", true)
                .setBaseUrl(HttpConfig.BASE_URL);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // 使用 Dex分包
        MultiDex.install(this);
    }
}