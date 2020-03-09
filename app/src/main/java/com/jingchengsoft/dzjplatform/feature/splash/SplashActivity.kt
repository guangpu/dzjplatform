package com.jingchengsoft.dzjplatform.feature.splash


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import com.gyf.immersionbar.BarHide
import com.hjq.permissions.OnPermission
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.jingchengsoft.dzjplatform.R
import com.jingchengsoft.dzjplatform.common.MyActivity
import com.jingchengsoft.dzjplatform.feature.home.HomeActivity
import com.jingchengsoft.dzjplatform.feature.login.LoginActivity
import com.jingchengsoft.dzjplatform.other.AppConfig
import com.jingchengsoft.dzjplatform.sp.SpUser
import kotlinx.android.synthetic.main.activity_splash.*


/**
 * @author xxh
 * @date 2019/11/7
 * @desc TODO.
 */

class SplashActivity : MyActivity(), OnPermission {

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        // 设置动画监听
        iv_splash_lottie.addAnimatorListener(object : AnimatorListenerAdapter() {

            override fun onAnimationEnd(animation: Animator) {
                requestPermission()
            }
        })

        // 设置状态栏和导航栏参数
        statusBarConfig
            // 有导航栏的情况下，activity全屏显示，也就是activity最下面被导航栏覆盖，不写默认非全屏
            .fullScreen(true)
            // 隐藏状态栏
            .hideBar(BarHide.FLAG_HIDE_STATUS_BAR)
            // 透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
            .transparentNavigationBar()
            .init()
    }

    override fun initData() {
        if (AppConfig.isDebug()) {
            tv_splash_debug.visibility = View.VISIBLE
        } else {
            tv_splash_debug.visibility = View.INVISIBLE
        }
    }

    private fun requestPermission() {
        XXPermissions.with(this)
            .permission(*Permission.Group.STORAGE)
            .request(this)
    }

    /**
     * [OnPermission]
     */

    override fun hasPermission(granted: List<String>?, isAll: Boolean) {
        if (SpUser.token != "") {
            HomeActivity.start()
        } else {
            startActivity(LoginActivity::class.java)
        }
        finish()
    }

    override fun noPermission(denied: List<String>, quick: Boolean) {
        if (quick) {
            toast(R.string.common_permission_fail)
            XXPermissions.gotoPermissionSettings(this@SplashActivity, true)
        } else {
            toast(R.string.common_permission_hint)
            postDelayed({ this.requestPermission() }, 1000)
        }
    }

    override fun onBackPressed() {
        //禁用返回键
        //super.onBackPressed();
    }

    override fun onRestart() {
        super.onRestart()
        if (XXPermissions.isHasPermission(this@SplashActivity, *Permission.Group.STORAGE)) {
            hasPermission(null, true)
        } else {
            requestPermission()
        }
    }
}