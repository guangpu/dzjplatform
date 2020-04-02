package com.jingchengsoft.dzjplatform.feature.home.function.statistic.fragment;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyFragment;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.activity.StatisticActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.chart.EchartView;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.entity.HiddenDanger;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.utils.RiskEchartOptionUtil;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.utils.StatisticHttpUtil;
import com.jingchengsoft.dzjplatform.http.ApiResponse;
import com.jingchengsoft.dzjplatform.http.CommonException;
import com.jingchengsoft.dzjplatform.http.PretreatmentCallback;

import java.util.List;

import butterknife.BindView;

/**
 * author : wgp
 * time   :  2020/4/1
 * desc   :
 */
public class DangerStatisticFragment extends MyFragment<StatisticActivity> {

    public static DangerStatisticFragment newInstance() {
        return new DangerStatisticFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_risk_statistic;
    }

    @BindView(R.id.lineChart)
    EchartView lineChart;

    @Override
    protected void initView() {
        getData();
        lineChart.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //最好在h5页面加载完毕后再加载数据，防止html的标签还未加载完成，不能正常显示
                getData();
            }
        });
    }

    @Override
    protected void initData() {

    }

    private void getData() {
        StatisticHttpUtil.getDangerStatisticData(new PretreatmentCallback<String>() {
            @Override
            public void onResponse(@NonNull ApiResponse response) {
                if(response.getData()!=null) {
                    List<HiddenDanger> dataList = JSON.parseArray(response.getData(), HiddenDanger.class);
                    refreshLineChart(dataList);
                }
            }

            @Override
            public void onException(CommonException e) {

            }
        });
    }

    private void refreshLineChart(List<HiddenDanger> dataList){
        lineChart.refreshEchartsWithOption(RiskEchartOptionUtil.getDangerBarChartOption(dataList));
    }
}
