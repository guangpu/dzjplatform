package com.jingchengsoft.dzjplatform.feature.home.function.statistic.chart;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.jingchengsoft.dzjplatform.feature.home.function.statistic.entity.RiskDanger;

import java.text.DecimalFormat;
import java.util.List;

/**
 * author : wgp
 * time   :  2020/4/1
 * desc   :
 */
public class RiskValueFormatter extends ValueFormatter {

    //private final DecimalFormat mFormat;
    private String suffix;
    private List<RiskDanger> dangers;

    public RiskValueFormatter(String suffix, List<RiskDanger> dangerList) {
        //mFormat = new DecimalFormat("###,###,###,##0.0");
        this.suffix = suffix;
        this.dangers = dangerList;
    }

    @Override
    public String getFormattedValue(float value) {
        return value + suffix;
    }

    @Override
    public String getAxisLabel(float value, AxisBase axis) {
        if (axis instanceof XAxis) {
            if(value > 0 && value%2 == 1) {
                if((int)value <dangers.size()) {
                    return dangers.get((int)value).getProject_name();
                } else {
                    return suffix;
                }
            } else {
                return "";
            }

        } else if (value > 0) {
            return value + suffix;
        } else {
            return value +"";
        }
    }
}
