package com.jingchengsoft.dzjplatform.feature.home

import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.blankj.utilcode.util.ActivityUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hjq.base.BaseFragmentAdapter
import com.jingchengsoft.dzjplatform.R
import com.jingchengsoft.dzjplatform.common.MyActivity
import com.jingchengsoft.dzjplatform.common.MyFragment
import com.jingchengsoft.dzjplatform.feature.home.function.FunctionFragment
import com.jingchengsoft.dzjplatform.feature.home.me.MeFragment
import com.jingchengsoft.dzjplatform.helper.ActivityStackManager
import com.jingchengsoft.dzjplatform.helper.DoubleClickHelper
import com.jingchengsoft.dzjplatform.other.KeyboardWatcher
import kotlinx.android.synthetic.main.activity_home.*

/**
 * @author xxh
 * @date 2019/11/6
 * @desc 主界面.
 */
class HomeActivity : MyActivity(),
    ViewPager.OnPageChangeListener,
    BottomNavigationView.OnNavigationItemSelectedListener,
    KeyboardWatcher.SoftKeyboardStateListener {

    companion object {
        fun start() {
            ActivityUtils.startActivity(HomeActivity::class.java)
        }
    }

    //ViewPager 适配器
    private lateinit var mPagerAdapter: BaseFragmentAdapter<MyFragment<*>>

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }


    override fun initView() {
        vp_home_pager.addOnPageChangeListener(this)
        // 不使用图标默认变色
        bv_home_navigation.itemIconTintList = null
        bv_home_navigation.setOnNavigationItemSelectedListener(this)
        KeyboardWatcher.with(this)
            .setListener(this)
    }

    override fun initData() {
        mPagerAdapter = BaseFragmentAdapter(this)
//        mPagerAdapter.addFragment(MessageFragment.newInstance())
//        mPagerAdapter.addFragment(NewsFragment.newInstance())
        mPagerAdapter.addFragment(FunctionFragment.newInstance())
//        mPagerAdapter.addFragment(AddressBookFragment.newInstance())
        mPagerAdapter.addFragment(MeFragment.newInstance())
//
        vp_home_pager.adapter = mPagerAdapter
        vp_home_pager.currentItem = 2
        // 限制页面数量
        vp_home_pager.offscreenPageLimit = mPagerAdapter.count

    }


    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        when (position) {
            0 -> bv_home_navigation.selectedItemId = R.id.home_message
            1 -> bv_home_navigation.selectedItemId = R.id.home_news
            2 -> bv_home_navigation.selectedItemId = R.id.home_function
            3 -> bv_home_navigation.selectedItemId = R.id.home_address_book
            4 -> bv_home_navigation.selectedItemId = R.id.home_me
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
//            R.id.home_message -> {
//                mPagerAdapter.setCurrentItem(MessageFragment::class.java)
//                return true
//            }
//            R.id.home_news -> {
//                mPagerAdapter.setCurrentItem(NewsFragment::class.java)
//                return true
//            }
            R.id.home_function -> {
                mPagerAdapter.setCurrentItem(FunctionFragment::class.java)
                return true
            }
//            R.id.home_address_book -> {
//                mPagerAdapter.setCurrentItem(AddressBookFragment::class.java)
//                return true
//            }
            R.id.home_me -> {
                mPagerAdapter.setCurrentItem(MeFragment::class.java)
                return true
            }
            else -> {
            }
        }

        return false
    }


    override fun onSoftKeyboardOpened(keyboardHeight: Int) {
        bv_home_navigation.visibility = View.GONE
    }

    override fun onSoftKeyboardClosed() {
        bv_home_navigation.visibility = View.VISIBLE
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        // 回调当前 Fragment 的 onKeyDown 方法
        return if (mPagerAdapter.currentFragment.onKeyDown(keyCode, event)) {
            true
        } else super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() {
        if (DoubleClickHelper.isOnDoubleClick()) {
            //移动到上一个任务栈，避免侧滑引起的不良反应
            moveTaskToBack(false)
            postDelayed({

                // 进行内存优化，销毁掉所有的界面
                ActivityStackManager.getInstance().finishAllActivities()
                // 销毁进程（注意：调用此 API 可能导致当前 Activity onDestroy 方法无法正常回调）
                // System.exit(0);

            }, 300)
        } else {
            toast(R.string.home_exit_hint)
        }
    }

    override fun onDestroy() {
        vp_home_pager.adapter = null
        vp_home_pager.removeOnPageChangeListener(this)
        bv_home_navigation.setOnNavigationItemSelectedListener(null)
        super.onDestroy()
    }


}
