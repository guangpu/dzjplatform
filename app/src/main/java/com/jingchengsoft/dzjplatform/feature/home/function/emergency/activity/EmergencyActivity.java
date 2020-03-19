package com.jingchengsoft.dzjplatform.feature.home.function.emergency.activity;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.ActivityUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.hjq.base.BaseFragmentAdapter;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyActivity;
import com.jingchengsoft.dzjplatform.common.MyFragment;
import com.jingchengsoft.dzjplatform.feature.home.function.emergency.fragment.PractiseCommentFragment;
import com.jingchengsoft.dzjplatform.feature.home.function.emergency.fragment.PractisePlanFragment;
import com.jingchengsoft.dzjplatform.feature.home.function.emergency.fragment.PractiseRecordFragment;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.activity.LeaderCheckAddActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.activity.SafeCheckAddActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.fragment.LeaderCheckFragment;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.fragment.SafeCheckFragment;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.fragment.SafeLogFragment;

import butterknife.BindView;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  应急管理
 */
public class EmergencyActivity extends MyActivity implements ViewPager.OnPageChangeListener {
    private BaseFragmentAdapter<MyFragment> mPagerAdapter;
    private String[] mTitles = {"演练计划", "演练记录", "演练评价"};
    private int currentItem = 0;

    public static void start() {
        ActivityUtils.startActivity(EmergencyActivity.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_emergency;
    }

    @BindView(R.id.vp_home_pager)
    ViewPager viewPager;
    @BindView(R.id.tab)
    SlidingTabLayout tab;

    @Override
    protected void initView() {
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    protected void initData() {
        mPagerAdapter = new BaseFragmentAdapter<>(this);
        mPagerAdapter.addFragment(PractisePlanFragment.newInstance());
        mPagerAdapter.addFragment(PractiseRecordFragment.newInstance());
        mPagerAdapter.addFragment(PractiseCommentFragment.newInstance());

        viewPager.setAdapter(mPagerAdapter);
        viewPager.setCurrentItem(0);
        // 限制页面数量
        viewPager.setOffscreenPageLimit(mPagerAdapter.getCount());
        tab.setViewPager(viewPager, mTitles);
    }

    @Override
    public void onRightClick(View v) {
//        if(currentItem == 0) LeaderCheckAddActivity.start();
//        if(currentItem == 1) SafeCheckAddActivity.start();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentItem = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
