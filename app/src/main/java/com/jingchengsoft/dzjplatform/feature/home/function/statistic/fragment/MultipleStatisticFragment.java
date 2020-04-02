package com.jingchengsoft.dzjplatform.feature.home.function.statistic.fragment;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyFragment;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.activity.StatisticActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.chart.EchartView;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.entity.AccidentAnalysis;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.entity.AccidentPercent;
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
public class MultipleStatisticFragment extends MyFragment<StatisticActivity> {

    public static MultipleStatisticFragment newInstance() {
        return new MultipleStatisticFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_multiple_statistic;
    }

    @BindView(R.id.accidentPercentPieChart)
    EchartView accidentPercentPieChart;
    @BindView(R.id.accidentNumBarChart)
    EchartView accidentNumBarChart;
    @BindView(R.id.accidentAnalysisLineChart)
    EchartView accidentAnalysisLineChart;

    @Override
    protected void initView() {
//        getAccidentAnalysisData();
//        accidentPercentPieChart.setWebViewClient(new WebViewClient(){
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                //最好在h5页面加载完毕后再加载数据，防止html的标签还未加载完成，不能正常显示
//                getAccidentPercentData();
//            }
//        });
//
//        accidentNumBarChart.setWebViewClient(new WebViewClient(){
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                //最好在h5页面加载完毕后再加载数据，防止html的标签还未加载完成，不能正常显示
//                getAccidentPercentData();
//            }
//        });

        accidentAnalysisLineChart.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //最好在h5页面加载完毕后再加载数据，防止html的标签还未加载完成，不能正常显示
                getAccidentAnalysisData();
            }
        });
    }

    @Override
    protected void initData() {

    }

    private void getAccidentPercentData() {
        StatisticHttpUtil.getAccidentPercentStatisticData(new PretreatmentCallback<String>() {
            @Override
            public void onResponse(@NonNull ApiResponse response) {
                if(response.getData()!=null) {
                    List<AccidentPercent> dataList = JSON.parseArray(response.getData(), AccidentPercent.class);
                    refreshAccidentPercentPieChart(dataList);
                    refreshAccidentNumBarChart(dataList);
                }
            }

            @Override
            public void onException(CommonException e) {

            }
        });
    }

    private void getAccidentAnalysisData() {
        StatisticHttpUtil.getAccidentAnalysisStatisticData(new PretreatmentCallback<String>() {
            @Override
            public void onResponse(@NonNull ApiResponse response) {
                if(response.getData()!=null) {
                    List<AccidentAnalysis> dataList = JSON.parseArray(response.getData(), AccidentAnalysis.class);
                    refreshAccidentAnalysisLineChart(dataList);
                }
            }

            @Override
            public void onException(CommonException e) {

            }
        });
    }

    private void refreshAccidentPercentPieChart(List<AccidentPercent> dataList){
        accidentPercentPieChart.refreshEchartsWithOption(RiskEchartOptionUtil.getAccidentPercentPieChartOptions(dataList));
    }

    private void refreshAccidentNumBarChart(List<AccidentPercent> dataList){
        accidentNumBarChart.refreshEchartsWithOption(RiskEchartOptionUtil.getAccidentNumBarChartOption(dataList));
    }

    private void refreshAccidentAnalysisLineChart(List<AccidentAnalysis> dataList) {
        Object[] x = new Object[]{
                "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"
        };
        Object[] y = new Object[]{
                820, 932, 901, 934, 1290, 1330, 1320
        };
        accidentAnalysisLineChart.refreshEchartsWithOption(RiskEchartOptionUtil.getLineChartOptions(x,y));
    }
}
