package com.jingchengsoft.dzjplatform.feature.home.function.statistic.fragment;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.LogUtils;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyFragment;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.activity.StatisticActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.chart.EchartView;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.entity.RiskDanger;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.utils.RiskEchartOptionUtil;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.utils.StatisticHttpUtil;
import com.zhouyou.http.callback.CallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author : wgp
 * time   :  2020/4/1
 * desc   :
 */
public class PersonDeviceStatisticFragment extends MyFragment<StatisticActivity> {

    public static PersonDeviceStatisticFragment newInstance() {
        return new PersonDeviceStatisticFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_person_device_statistic;
    }

    @BindView(R.id.questionPieChart)
    EchartView questionPieChart;
    @BindView(R.id.healthyPieChart)
    EchartView healthyPieChart;
    @BindView(R.id.deviceBarChart)
    EchartView deviceBarChart;

    @Override
    protected void initView() {
//        getData();
        questionPieChart.setWebViewClient(new WebViewClient(){
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

    }

    private void refreshQuestionPieChart(List<RiskDanger> dataList){
        questionPieChart.refreshEchartsWithOption(RiskEchartOptionUtil.getRiskBarChartOption(dataList));
    }
}
