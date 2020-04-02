package com.jingchengsoft.dzjplatform.feature.home.function.statistic.utils;

import com.blankj.utilcode.util.LogUtils;
import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Toolbox;
import com.github.abel533.echarts.Tooltip;
import com.github.abel533.echarts.axis.Axis;
import com.github.abel533.echarts.axis.AxisLabel;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.SeriesType;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.feature.DataView;
import com.github.abel533.echarts.feature.Feature;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.feature.Restore;
import com.github.abel533.echarts.feature.SaveAsImage;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.series.Series;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.entity.AccidentAnalysis;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.entity.AccidentPercent;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.entity.HiddenDanger;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.entity.RiskDanger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author : wgp
 * time   :  2020/4/1
 * desc   :
 */
public class RiskEchartOptionUtil {

    /**
     * 历年事故分析折线图
     * @return
     */
    public static GsonOption getAccidentAnalysisLineChartOption(List<AccidentAnalysis> dataList) {
        //http://echarts.baidu.com/echarts2/doc/example/mix1.html
        GsonOption option = new GsonOption();
        //title
        String text = "历年事故分析";
        String subText = "";
        option.title(text,subText);
        //tooltip
        Tooltip tooltip = new Tooltip();
        tooltip.trigger(Trigger.axis);
        option.tooltip(tooltip);
        //toolbox
        Toolbox toolbox = new Toolbox();
        toolbox.show(true);
        Map<String, Feature> feature = new HashMap<String, Feature>();
        feature.put("mark",new Feature().show(true));
        feature.put("dataView",new DataView().show(true).readOnly(false));
        feature.put("magicType",new MagicType(Magic.line, Magic.bar).show(true));
        feature.put("restore",new Restore().show(true));
        feature.put("saveAsImage",new SaveAsImage().show(false));
        toolbox.setFeature(feature);
        option.toolbox(toolbox);
        //calculable
        option.setCalculable(true);
        //legend
        String legend1 = "重伤人数";
        String legend2 = "死亡人数";
        Legend legend = new Legend();
        legend.data(legend1,legend2);
        option.legend(legend);
        //grid
//            Grid grid = new Grid();
//            grid.y2(80);
//            option.grid(grid);
        //xAxis
        List<Axis> xAxis = new ArrayList<Axis>();
        CategoryAxis categoryAxis = new CategoryAxis();
        {
            List xAxisValues = new ArrayList();
            for (int i = 1; i <= dataList.size(); i++) {
                LogUtils.i("年："+dataList.get(i).getYear());
                xAxisValues.add(dataList.get(i).getYear());
            }
            categoryAxis.setData(xAxisValues);
        }
        xAxis.add(categoryAxis);
        option.xAxis(xAxis);
        //yAxis
        List<Axis> yAxis = new ArrayList<Axis>();
        {
            ValueAxis valueAxis = new ValueAxis();
            valueAxis.name("人数");
            yAxis.add(valueAxis);
        }
        option.yAxis(yAxis);
        //series
        List<Series> series = new ArrayList<Series>();
        {
            Line line = new Line();
            line.name(legend1).type(SeriesType.line).yAxisIndex(0);
            List data = new ArrayList();
            for (AccidentAnalysis aa : dataList){
                data.add(aa.getZsrs());
            }
            line.setData(data);
            series.add(line);
        }
        {
            Line line = new Line();
            line.name(legend2).type(SeriesType.line).yAxisIndex(0);
            List data = new ArrayList();
            for (AccidentAnalysis aa : dataList){
                data.add(aa.getSwrs());
            }
            line.setData(data);
            series.add(line);
        }
        option.series(series);
        //
        return option;
    }

    /**
     * 各事故数量统计
     * @param dataList
     * @return
     */
    public static GsonOption getAccidentNumBarChartOption(List<AccidentPercent> dataList) {
        GsonOption option = new GsonOption();
        //title
        String text = "各事故数量统计";
        String subText = "";
        option.title(text,subText);
        //tooltip
        Tooltip tooltip = new Tooltip();
        tooltip.trigger(Trigger.axis);
        option.tooltip(tooltip);
        //toolbox
        Toolbox toolbox = new Toolbox();
        toolbox.show(true);
        Map<String, Feature> feature = new HashMap<String, Feature>();
        feature.put("mark",new Feature().show(true));
        feature.put("dataView",new DataView().show(true).readOnly(false));
        feature.put("magicType",new MagicType(Magic.line, Magic.bar).show(true));
        feature.put("restore",new Restore().show(true));
        feature.put("saveAsImage",new SaveAsImage().show(false));
        toolbox.setFeature(feature);
        option.toolbox(toolbox);
        //calculable
        option.setCalculable(true);
        //legend
//        String legend1 = "领导带班检查";
//        Legend legend = new Legend();
//        legend.data(legend1);
//        option.legend(legend);
        //xAxis
        List<Axis> xAxis = new ArrayList<Axis>();
        CategoryAxis categoryAxis = new CategoryAxis();
        {
            List xAxisValues = new ArrayList();
            for(AccidentPercent rd:dataList) {
                xAxisValues.add(rd.getCatagory());
            }
            categoryAxis.setData(xAxisValues);
        }
        xAxis.add(categoryAxis);
        option.xAxis(xAxis);
        //yAxis
        List<Axis> yAxis = new ArrayList<Axis>();
        {
            ValueAxis valueAxis = new ValueAxis();
            yAxis.add(valueAxis);
        }
        option.yAxis(yAxis);
        //series
        List<Series> series = new ArrayList<Series>();
        {
            Bar bar = new Bar();
            //bar.name(legend1).type(SeriesType.bar).yAxisIndex(0);
            List data = new ArrayList();
            for (AccidentPercent rd : dataList){
                data.add(rd.getFxs());
            }
            bar.setData(data);
            series.add(bar);
        }
        option.series(series);
        //
        return option;
    }


    /**
     * 各事故类型占比饼图
     * @param dataList
     * @return
     */
    public static GsonOption getAccidentPercentPieChartOptions(List<AccidentPercent> dataList) {
        GsonOption option = new GsonOption();
        option.title("各事故类型所占比");
        option.title().setX(X.center);

        option.tooltip().trigger(Trigger.item);
        option.tooltip().formatter("{a} {b}:{c} ({d}%)");

        Object[] oneData = new Object[dataList.size()];
        List<Map<String, Object>> twoData = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            oneData[i] = dataList.get(i).getCatagory();
            Map<String, Object> data = new HashMap<>();
            data.put("value", dataList.get(i).getFxs());
            data.put("name", dataList.get(i).getCatagory());
            twoData.add(data);
        }

        option.legend().left(X.left).orient(Orient.vertical);
        option.legend().data(oneData);

        Pie pie = new Pie();
        pie.name("各事故类型所占比");
        pie.type(SeriesType.pie);
        pie.center("50%", "70%").radius("55%");
        pie.itemStyle().emphasis().shadowBlur(10).shadowOffsetX(0).shadowColor("rgba(0, 0, 0, 0.5)");
        pie.setData(twoData);
        option.series(pie);
        return option;
    }

    public static GsonOption getBingTuChartOptions(Object[] data, List<Map<String, Object>> yAxis) {
        GsonOption option = new GsonOption();
        option.title("支付方式占比");
        option.title().setX(X.center);

        option.tooltip().trigger(Trigger.item);
        option.tooltip().formatter("{a} {b}:{c} ({d}%)");

        option.legend().left(X.left).orient(Orient.vertical);
        option.legend().data(data);

        Pie pie = new Pie();
        pie.name("");
        pie.type(SeriesType.pie);
        pie.center("50%", "70%").radius("55%");
        pie.itemStyle().emphasis().shadowBlur(10).shadowOffsetX(0).shadowColor("rgba(0, 0, 0, 0.5)");
        pie.setData(yAxis);
        option.series(pie);
        return option;
    }

    public static GsonOption getDangerBarChartOption(List<HiddenDanger> dataList) {
        GsonOption option = new GsonOption();
        //title
        String text = "text";
        String subText = "subText";
        option.title(text,subText);
        //tooltip
        Tooltip tooltip = new Tooltip();
        tooltip.trigger(Trigger.axis);
        option.tooltip(tooltip);
        //toolbox
        Toolbox toolbox = new Toolbox();
        toolbox.show(true);
        Map<String, Feature> feature = new HashMap<String, Feature>();
        feature.put("mark",new Feature().show(true));
        feature.put("dataView",new DataView().show(true).readOnly(false));
        feature.put("magicType",new MagicType(Magic.line, Magic.bar).show(true));
        feature.put("restore",new Restore().show(true));
        feature.put("saveAsImage",new SaveAsImage().show(false));
        toolbox.setFeature(feature);
        option.toolbox(toolbox);
        //calculable
        option.setCalculable(true);
        //legend
        String legend1 = "领导带班检查";
        String legend2 = "安全专项检查";
        String legend3 = "日常检查";
        Legend legend = new Legend();
        legend.data(legend1,legend2,legend3);
        option.legend(legend);
        //xAxis
        List<Axis> xAxis = new ArrayList<Axis>();
        CategoryAxis categoryAxis = new CategoryAxis();
        {
            List xAxisValues = new ArrayList();
            for(HiddenDanger rd:dataList) {
                xAxisValues.add(rd.getProject_name());
            }
            categoryAxis.setData(xAxisValues);
        }
        xAxis.add(categoryAxis);
        option.xAxis(xAxis);
        //yAxis
        List<Axis> yAxis = new ArrayList<Axis>();
        {
            ValueAxis valueAxis = new ValueAxis();
            yAxis.add(valueAxis);
        }
        option.yAxis(yAxis);
        //series
        List<Series> series = new ArrayList<Series>();
        {
            Bar bar = new Bar();
            bar.name(legend1).type(SeriesType.bar).yAxisIndex(0);
            List data = new ArrayList();
            for (HiddenDanger rd : dataList){
                data.add(rd.getLddbcount());
            }
            bar.setData(data);
            series.add(bar);
        }
        {
            Bar bar = new Bar();
            bar.name(legend2).type(SeriesType.bar).yAxisIndex(0);
            List data = new ArrayList();
            for (HiddenDanger rd : dataList){
                data.add(rd.getAqzxcount());
            }
            bar.setData(data);
            series.add(bar);
        }
        {
            Bar bar = new Bar();
            bar.name(legend3).type(SeriesType.bar).yAxisIndex(0);
            List data = new ArrayList();
            for (HiddenDanger rd : dataList){
                data.add(rd.getRccount());
            }
            bar.setData(data);
            series.add(bar);
        }
        option.series(series);
        //
        return option;
    }

    public static GsonOption getRiskBarChartOption(List<RiskDanger> dataList) {
        GsonOption option = new GsonOption();
        //title
        String text = "text";
        String subText = "subText";
        option.title(text,subText);
        //tooltip
        Tooltip tooltip = new Tooltip();
        tooltip.trigger(Trigger.axis);
        option.tooltip(tooltip);
        //toolbox
        Toolbox toolbox = new Toolbox();
        toolbox.show(true);
        Map<String, Feature> feature = new HashMap<String, Feature>();
        feature.put("mark",new Feature().show(true));
        feature.put("dataView",new DataView().show(true).readOnly(false));
        feature.put("magicType",new MagicType(Magic.line, Magic.bar).show(true));
        feature.put("restore",new Restore().show(true));
        feature.put("saveAsImage",new SaveAsImage().show(false));
        toolbox.setFeature(feature);
        option.toolbox(toolbox);
        //calculable
        option.setCalculable(true);
        //legend
        String legend1 = "危险源数量";
        String legend2 = "重大危险源数量";
        Legend legend = new Legend();
        legend.data(legend1,legend2);
        option.legend(legend);
        //xAxis
        List<Axis> xAxis = new ArrayList<Axis>();
        CategoryAxis categoryAxis = new CategoryAxis();
        {
            List xAxisValues = new ArrayList();
            for(RiskDanger rd:dataList) {
                xAxisValues.add(rd.getProject_name());
            }
            categoryAxis.setData(xAxisValues);
        }
        xAxis.add(categoryAxis);
        option.xAxis(xAxis);
        //yAxis
        List<Axis> yAxis = new ArrayList<Axis>();
        {
            ValueAxis valueAxis = new ValueAxis();
            yAxis.add(valueAxis);
        }
        option.yAxis(yAxis);
        //series
        List<Series> series = new ArrayList<Series>();
        {
            Bar bar = new Bar();
            bar.name(legend1).type(SeriesType.bar).yAxisIndex(0);
            List data = new ArrayList();
            for (RiskDanger rd : dataList){
                data.add(rd.getDangercount());
            }
            bar.setData(data);
            series.add(bar);
        }
        {
            Bar bar = new Bar();
            bar.name(legend2).type(SeriesType.bar).yAxisIndex(0);
            List data = new ArrayList();
            for (RiskDanger rd : dataList){
                data.add(rd.getMajordangercount());
            }
            bar.setData(data);
            series.add(bar);
        }
        option.series(series);
        //
        return option;
    }

    /**根据https://mvnrepository.com/artifact/com.github.abel533/ECharts
     * 结合http://echarts.baidu.com/examples.html官方实例
     * 配置json数据
     * @return
     */
    public static GsonOption getLineAndBarChartOption() {
        //http://echarts.baidu.com/echarts2/doc/example/mix1.html
        GsonOption option = new GsonOption();
        //title
        String text = "text";
        String subText = "subText";
        option.title(text,subText);
        //tooltip
        Tooltip tooltip = new Tooltip();
        tooltip.trigger(Trigger.axis);
        option.tooltip(tooltip);
        //toolbox
        Toolbox toolbox = new Toolbox();
        toolbox.show(true);
        Map<String, Feature> feature = new HashMap<String, Feature>();
        feature.put("mark",new Feature().show(true));
        feature.put("dataView",new DataView().show(true).readOnly(false));
        feature.put("magicType",new MagicType(Magic.line, Magic.bar).show(true));
        feature.put("restore",new Restore().show(true));
        feature.put("saveAsImage",new SaveAsImage().show(false));
        toolbox.setFeature(feature);
        option.toolbox(toolbox);
        //calculable
        option.setCalculable(true);
        //legend
        String legend1 = "蒸发量";
        String legend2 = "降水量";
        String legend3 = "平均温度";
        Legend legend = new Legend();
        legend.data(legend1,legend2,legend3);
        option.legend(legend);
        //grid
//            Grid grid = new Grid();
//            grid.y2(80);
//            option.grid(grid);
        //xAxis
        List<Axis> xAxis = new ArrayList<Axis>();
        CategoryAxis categoryAxis = new CategoryAxis();
        {
            List xAxisValues = new ArrayList();
            for (int i = 1; i <= 12; i++) {
                xAxisValues.add(i + "月");
            }
            categoryAxis.setData(xAxisValues);
        }
        xAxis.add(categoryAxis);
        option.xAxis(xAxis);
        //yAxis
        List<Axis> yAxis = new ArrayList<Axis>();
        {
            ValueAxis valueAxis = new ValueAxis();
            valueAxis.name("水量");
            valueAxis.axisLabel(new AxisLabel().formatter("{value} ml"));
            yAxis.add(valueAxis);
        }
        {
            ValueAxis valueAxis = new ValueAxis();
            valueAxis.name("温度");
            valueAxis.axisLabel(new AxisLabel().formatter("{value} °C"));
            yAxis.add(valueAxis);
        }
        option.yAxis(yAxis);
        //series
        List<Series> series = new ArrayList<Series>();
        {
            Bar bar = new Bar();
            bar.name(legend1).type(SeriesType.bar).yAxisIndex(0);
            List data = new ArrayList();
            double arrays[] = {2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3};
            for (double value : arrays){
                data.add(value);
            }
            bar.setData(data);
            series.add(bar);
        }
        {
            Bar bar = new Bar();
            bar.name(legend2).type(SeriesType.bar).yAxisIndex(0);
            List data = new ArrayList();
            double arrays[] = {2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3};
            for (double value : arrays){
                data.add(value);
            }
            bar.setData(data);
            series.add(bar);
        }
        {
            Line line = new Line();
            line.name(legend3).type(SeriesType.line).yAxisIndex(1);
            List data = new ArrayList();
            double arrays[] = {2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2};
            for (double value : arrays){
                data.add(value);
            }
            line.setData(data);
            series.add(line);
        }
        option.series(series);
        //
        return option;
    }

    public static GsonOption getLineChartOptions(Object[] xAxis, Object[] yAxis) {
        GsonOption option = new GsonOption();
        option.title("折线图");
        option.legend("销量");
        option.tooltip().trigger(Trigger.axis);

        ValueAxis valueAxis = new ValueAxis();
        option.yAxis(valueAxis);

        CategoryAxis categorxAxis = new CategoryAxis();
        categorxAxis.axisLine().onZero(false);
        categorxAxis.boundaryGap(true);
        categorxAxis.data(xAxis);
        option.xAxis(categorxAxis);

        Line line = new Line();
        line.smooth(false).name("销量").data(yAxis).itemStyle().normal().lineStyle().shadowColor("rgba(0,0,0,0.4)");
        option.series(line);
        return option;
    }
}
