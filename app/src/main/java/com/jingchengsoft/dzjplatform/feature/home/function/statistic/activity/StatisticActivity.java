package com.jingchengsoft.dzjplatform.feature.home.function.statistic.activity;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.ActivityUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.hjq.base.BaseFragmentAdapter;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyActivity;
import com.jingchengsoft.dzjplatform.common.MyFragment;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.activity.LeaderCheckAddActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.activity.SafeCheckAddActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.fragment.LeaderCheckFragment;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.fragment.SafeCheckFragment;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.fragment.SafeLogFragment;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.fragment.DangerStatisticFragment;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.fragment.MultipleStatisticFragment;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.fragment.RiskManageStatisticFragment;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.fragment.RiskStatisticFragment;

import butterknife.BindView;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  统计分析
 */
public class StatisticActivity extends MyActivity implements ViewPager.OnPageChangeListener {
    private BaseFragmentAdapter<MyFragment> mPagerAdapter;
//    private String[] mTitles = {"风险管控分析", "隐患统计分析", "事故综合分析", "人员设备分析"};
    private String[] mTitles = {"风险管控分析","隐患统计分析","事故综合分析"};
    private int currentItem = 0;

    public static void start() {
        ActivityUtils.startActivity(StatisticActivity.class);
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
        mPagerAdapter.addFragment(RiskStatisticFragment.newInstance());
        mPagerAdapter.addFragment(DangerStatisticFragment.newInstance());
        mPagerAdapter.addFragment(MultipleStatisticFragment.newInstance());

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
