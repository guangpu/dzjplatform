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
public class RiskStatisticFragment extends MyFragment<StatisticActivity> {

    public static RiskStatisticFragment newInstance() {
        return new RiskStatisticFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_risk_statistic;
    }

    @BindView(R.id.lineChart)
    EchartView lineChart;

    @Override
    protected void initView() {
//        getData();
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
        StatisticHttpUtil.getRiskStatisticData(new CallBack<String>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(ApiException e) {

            }

            @Override
            public void onSuccess(String o) {
                LogUtils.i(o);
                try {
                    JSONObject jsonObject = new JSONObject(o);
                    if (jsonObject.getString("code").equals("000000")) {
                        List<RiskDanger> list = JSON.parseArray(jsonObject.getString("danger"), RiskDanger.class);
                        List<RiskDanger> majorList = JSON.parseArray(jsonObject.getString("mainDanger"), RiskDanger.class);
                        List<RiskDanger> rsList = new ArrayList<>();
                        if(list.size()>0 && majorList.size() >0) {
                            for (RiskDanger rd:list) {
                                for(RiskDanger rdm:majorList) {
                                    if(rdm.getProject_id().equals(rd.getProject_id()) && rdm.getProject_name().equals(rd.getProject_name())) {
                                        rd.setMajordangercount(rdm.getMajordangercount());
                                    }
                                }
                                rsList.add(rd);
                            }
                        } else {
                            rsList = list;
                        }
                        refreshLineChart(rsList);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void refreshLineChart(List<RiskDanger> dataList){
        lineChart.refreshEchartsWithOption(RiskEchartOptionUtil.getRiskBarChartOption(dataList));
    }
}
