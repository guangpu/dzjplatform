package com.jingchengsoft.dzjplatform.feature.home.function.statistic.fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyFragment;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.activity.StatisticActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.chart.RiskValueFormatter;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.entity.RiskDanger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author : wgp
 * time   :  2020/4/1
 * desc   :  风险管控分析
 */
public class RiskManageStatisticFragment extends MyFragment<StatisticActivity> {

    public static RiskManageStatisticFragment newInstance() {
        return new RiskManageStatisticFragment();
    }

    private List<RiskDanger> dangers = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_risk_manage_statistic;
    }

    @BindView(R.id.chart)
    BarChart mBarChart;

    @Override
    protected void initView() {
        initChart();
    }

    @Override
    protected void initData() {

        RiskDanger danger = new RiskDanger();
        danger.setProject_id("1");
        danger.setProject_name("安全环保科");
        danger.setDangercount(23);
        danger.setMajordangercount(34);

        RiskDanger danger1 = new RiskDanger();
        danger1.setProject_id("2");
        danger1.setProject_name("工程管理科");
        danger1.setDangercount(41);
        danger1.setMajordangercount(30);

        RiskDanger danger2 = new RiskDanger();
        danger2.setProject_id("3");
        danger2.setProject_name("质量监督科");
        danger2.setDangercount(22);
        danger2.setMajordangercount(18);

        dangers.add(danger);
        dangers.add(danger1);
        dangers.add(danger2);

        ArrayList<BarEntry> valsComp1 = new ArrayList<>();
        valsComp1.add(new BarEntry(0, danger.getDangercount(), danger));
        valsComp1.add(new BarEntry(1, danger.getMajordangercount(), danger1));
        valsComp1.add(new BarEntry(2, danger1.getDangercount(), danger2));
        valsComp1.add(new BarEntry(3, danger1.getMajordangercount(), danger2));
        valsComp1.add(new BarEntry(4, danger2.getDangercount(), danger2));
        valsComp1.add(new BarEntry(5, danger2.getMajordangercount(), danger2));

        setData(valsComp1);
    }

    private void initChart() {
        //设置表格上的点，被点击的时候，的回调函数
        //mBarChart.setOnChartValueSelectedListener(this);
        mBarChart.setDrawBarShadow(false);
        mBarChart.setDrawValueAboveBar(true);
        mBarChart.getDescription().setEnabled(false);
        // 如果60多个条目显示在图表,drawn没有值
        mBarChart.setMaxVisibleValueCount(60);
        // 扩展现在只能分别在x轴和y轴
        mBarChart.setPinchZoom(false);
        //是否显示表格颜色
        mBarChart.setDrawGridBackground(false);
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        // 只有1天的时间间隔
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(7);
        xAxis.setAxisMaximum(10);
        xAxis.setAxisMinimum(0f);
        ValueFormatter xAxisFormatter = new RiskValueFormatter("test", dangers);

        xAxis.setValueFormatter(xAxisFormatter);

        YAxis leftAxis = mBarChart.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        //这个替换setStartAtZero(true)
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(50f);
        YAxis rightAxis = mBarChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(8, false);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f);
        rightAxis.setAxisMaximum(50f);
    }

    //设置数据
    private void setData(ArrayList yVals1) {
        float start = 1f;
        BarDataSet set1;
        if (mBarChart.getData() != null &&
                mBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mBarChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mBarChart.getData().notifyDataChanged();
            mBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "危险源统计");
            //设置有四种颜色
            set1.setColors(ColorTemplate.MATERIAL_COLORS);
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);
            //设置数据
            mBarChart.setData(data);
        }
    }
}
