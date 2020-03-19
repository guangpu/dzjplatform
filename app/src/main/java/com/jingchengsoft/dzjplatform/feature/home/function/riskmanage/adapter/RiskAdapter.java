package com.jingchengsoft.dzjplatform.feature.home.function.riskmanage.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.feature.home.function.riskmanage.entity.Risk;
import com.jingchengsoft.dzjplatform.feature.home.function.specialwork.entity.SpecialWork;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  风险管控adapter
 */
public class RiskAdapter extends BaseQuickAdapter<Risk, BaseViewHolder> {

    public RiskAdapter(@Nullable List<Risk> data) {
        super(R.layout.item_risk, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Risk risk) {
        baseViewHolder.setText(R.id.tv_project_name, risk.getProjectName());
        baseViewHolder.setText(R.id.tv_report_date, risk.getReportDate());
        baseViewHolder.setText(R.id.tv_report_people, risk.getReportDate());
    }
}
