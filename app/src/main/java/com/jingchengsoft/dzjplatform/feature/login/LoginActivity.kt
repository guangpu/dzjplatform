package com.jingchengsoft.dzjplatform.feature.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.StringUtils
import com.hjq.image.ImageLoader
import com.hjq.umeng.Platform
import com.hjq.umeng.UmengClient
import com.hjq.umeng.UmengLogin
import com.jingchengsoft.dzjplatform.R
import com.jingchengsoft.dzjplatform.common.MyActivity
import com.jingchengsoft.dzjplatform.feature.home.HomeActivity
import com.jingchengsoft.dzjplatform.http.ApiResponse
import com.jingchengsoft.dzjplatform.http.CommonException
import com.jingchengsoft.dzjplatform.http.HttpConfig
import com.jingchengsoft.dzjplatform.http.PretreatmentCallback
import com.jingchengsoft.dzjplatform.other.KeyboardWatcher
import com.jingchengsoft.dzjplatform.sp.SpUser
import com.jingchengsoft.dzjplatform.ui.dialog.WaitDialog
import com.zhouyou.http.EasyHttp
import kotlinx.android.synthetic.main.activity_login.*


/**
 * @author xxh
 * @date 2019/11/19
 * @desc TODO.
 */

class LoginActivity : MyActivity(), UmengLogin.OnLoginListener,
    KeyboardWatcher.SoftKeyboardStateListener {


    /** logo 缩放比例  */
    private val mLogoScale = 0.8f
    /** 动画时间  */
    private val mAnimTime = 300

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        tv_login_version.text = "V" + AppUtils.getAppVersionName()
//        InputTextHelper.with(this)
//            .addView(et_login_phone)
//            .addView(et_login_password)
//            .setMain(btn_login_commit)
//            .setListener({ helper -> et_login_phone.text.toString().length == 11 && et_login_password.text.toString().length >= 6 })
//            .build()

        post {
            // 因为在小屏幕手机上面，因为计算规则的因素会导致动画效果特别夸张，所以不在小屏幕手机上面展示这个动画效果
            if (v_login_blank.height > ll_login_body.height) {
                // 只有空白区域的高度大于登录框区域的高度才展示动画
                KeyboardWatcher.with(this@LoginActivity)
                    .setListener(this@LoginActivity)
            }
        }

        setOnClickListener(
//            R.id.tv_login_forget,
            R.id.btn_login_commit,
            R.id.iv_login_qq,
            R.id.iv_login_wx
        )
    }

    override fun initData() {
        // 判断用户当前有没有安装 QQ
        if (!UmengClient.isAppInstalled(this, Platform.QQ)) {
            iv_login_qq.visibility = View.GONE
        }

        // 判断用户当前有没有安装微信
        if (!UmengClient.isAppInstalled(this, Platform.WECHAT)) {
            iv_login_wx.visibility = View.GONE
        }

        // 如果这两个都没有安装就隐藏提示
        if (iv_login_qq.visibility == View.GONE && iv_login_wx.visibility == View.GONE) {
            ll_login_other.visibility = View.GONE
        }
    }

//    override fun onRightClick(v: View) {
//        // 跳转到注册界面
//        startActivityForResult(RegisterActivity::class.java) { resultCode, data ->
//            // 如果已经注册成功，就执行登录操作
//            if (resultCode === RESULT_OK && data != null) {
//                et_login_phone.setText(data.getStringExtra(IntentKey.PHONE))
//                et_login_password.setText(data.getStringExtra(IntentKey.PASSWORD))
//                onClick(btn_login_commit)
//            }
//        }
//    }

    override fun onClick(v: View) {
        when (v.id) {
//            R.id.tv_login_forget -> startActivity(PasswordForgetActivity::class.java)
            R.id.btn_login_commit -> {
                val username = et_login_user.text.toString()
                val pwd = et_login_password.text.toString()
                loginHttp(username, pwd)
//                HomeActivity.start()
//                finish()
//                if (et_login_phone.text.toString().length != 11) {
//                    toast(R.string.common_phone_input_error)
//                } else {
//                    val dialog = WaitDialog.Builder(this)
//                        .show()
//                    postDelayed({
//                        dialog.dismiss()
//                        // 处理登录
//
////                    startActivity(HomeActivity::class.java)
////                    finish()
//                    }, 2000)
//                }

            }
            R.id.iv_login_qq, R.id.iv_login_wx -> {

//                toast("记得改好第三方 AppID 和 AppKey，否则会调不起来哦")
//                val platform: Platform
//                when (v.id) {
//                    R.id.iv_login_qq -> platform = Platform.QQ
//                    R.id.iv_login_wx -> {
//                        platform = Platform.WECHAT
//                        toast("也别忘了改微信 " + WXEntryActivity::class.java.simpleName + " 类所在的包名哦")
//                    }
//                    else -> throw IllegalStateException("are you ok?")
//                }
//                UmengClient.login(this, platform, this)
            }
            else -> {
            }
        }
    }

    /**
     * 发起Http请求
     */
    private fun loginHttp(username: String, pwd: String) {
        if (StringUtils.isEmpty(username)) {
            toast("用户名不能为空！")
            return
        }
        if (StringUtils.isEmpty(pwd)) {
            toast("密码不能为空！")
            return
        }
        val dialog = WaitDialog.Builder(this)
            .show()
        EasyHttp.get("apiLogin")
            .baseUrl(HttpConfig.BASE_URL)
            .params("username", username)
            .params("password1", pwd)
            .execute(object : PretreatmentCallback<String>() {
                override fun onResponse(response: ApiResponse) {
                    LogUtils.i("token：" + response.data)
                    if (response.isOk) {
                        SpUser.username = username
                        SpUser.pwd = pwd
                        SpUser.token = response.data
                        postDelayed(
                            {

                                // 处理登录
                                startActivity(HomeActivity::class.java)
                                dialog.dismiss()
                                finish()
                            }, 1000
                        )
                    } else {
                        LogUtils.i("错误")
                        toast(response.msg)
                        dialog.dismiss()

                    }
                }

                override fun onException(e: CommonException) {
                    e.exception.printStackTrace()
                    toast("请求异常")
                    dialog.dismiss()

                }

                override fun onCompleted() {
                }

            })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 友盟登录回调
        UmengClient.onActivityResult(this, requestCode, resultCode, data)
    }

    /**
     * [UmengLogin.OnLoginListener]
     */

    /**
     * 授权成功的回调
     *
     * @param platform      平台名称
     * @param data          用户资料返回
     */
    override fun onSucceed(platform: Platform, data: UmengLogin.LoginData) {
        // 判断第三方登录的平台
        when (platform) {
            Platform.QQ -> {
            }
            Platform.WECHAT -> {
            }
            else -> {
            }
        }

        ImageLoader.with(this)
            .load(data.avatar)
            .circle()
            .into(iv_login_logo)

        toast("昵称：" + data.name + "\n" + "性别：" + data.sex)
        toast("id：" + data.id)
        toast("token：" + data.token)
    }

    /**
     * 授权失败的回调
     *
     * @param platform      平台名称
     * @param t             错误原因
     */
    override fun onError(platform: Platform, t: Throwable) {
        toast("第三方登录出错：" + t.message)
    }

    /**
     * 授权取消的回调
     *
     * @param platform      平台名称
     */
    override fun onCancel(platform: Platform) {
        toast("取消第三方登录")
    }

    /**
     * [KeyboardWatcher.SoftKeyboardStateListener]
     */

    override fun onSoftKeyboardOpened(keyboardHeight: Int) {
        val screenHeight = resources.displayMetrics.heightPixels
        val location = IntArray(2)
        // 获取这个 View 在屏幕中的坐标（左上角）
        ll_login_body.getLocationOnScreen(location)
        //int x = location[0];
        val y = location[1]
        val bottom = screenHeight - (y + ll_login_body.height)
        if (keyboardHeight > bottom) {
            // 执行位移动画
            val objectAnimator =
                ObjectAnimator.ofFloat(
                    ll_login_body,
                    "translationY",
                    0f,
                    -(keyboardHeight - bottom).toFloat()
                )
            objectAnimator.duration = mAnimTime.toLong()
            objectAnimator.interpolator = AccelerateDecelerateInterpolator()
            objectAnimator.start()

            // 执行缩小动画
            iv_login_logo.pivotX = iv_login_logo.width / 2f
            iv_login_logo.pivotY = iv_login_logo.height.toFloat()
            val animatorSet = AnimatorSet()
            val scaleX = ObjectAnimator.ofFloat(iv_login_logo, "scaleX", 1.0f, mLogoScale)
            val scaleY = ObjectAnimator.ofFloat(iv_login_logo, "scaleY", 1.0f, mLogoScale)
            val translationY =
                ObjectAnimator.ofFloat(
                    iv_login_logo,
                    "translationY",
                    0.0f,
                    -(keyboardHeight - bottom).toFloat()
                )
            animatorSet.play(translationY).with(scaleX).with(scaleY)
            animatorSet.duration = mAnimTime.toLong()
            animatorSet.start()
        }
    }

    override fun onSoftKeyboardClosed() {
        // 执行位移动画
        val objectAnimator =
            ObjectAnimator.ofFloat(ll_login_body, "translationY", ll_login_body.translationY, 0f)
        objectAnimator.duration = mAnimTime.toLong()
        objectAnimator.interpolator = AccelerateDecelerateInterpolator()
        objectAnimator.start()

        if (iv_login_logo.translationY == 0f) {
            return
        }
        // 执行放大动画
        iv_login_logo.pivotX = iv_login_logo.width / 2f
        iv_login_logo.pivotY = iv_login_logo.height.toFloat()
        val animatorSet = AnimatorSet()
        val scaleX = ObjectAnimator.ofFloat(iv_login_logo, "scaleX", mLogoScale, 1.0f)
        val scaleY = ObjectAnimator.ofFloat(iv_login_logo, "scaleY", mLogoScale, 1.0f)
        val translationY =
            ObjectAnimator.ofFloat(iv_login_logo, "translationY", iv_login_logo.translationY, 0f)
        animatorSet.play(translationY).with(scaleX).with(scaleY)
        animatorSet.duration = mAnimTime.toLong()
        animatorSet.start()
    }
}