package com.jingchengsoft.dzjplatform.feature.home.function.riskmanage.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.feature.home.function.riskmanage.entity.Risk;

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
        baseViewHolder.setText(R.id.tv_comp_id, risk.getComp_id());
        baseViewHolder.setText(R.id.tv_danger_rs_des, risk.getDanger_rs_des());
        baseViewHolder.setText(R.id.tv_plan_start, risk.getPlan_start());
        baseViewHolder.setText(R.id.tv_plan_end, risk.getPlan_start());
        baseViewHolder.setText(R.id.tv_actual_start, risk.getActual_start());
        baseViewHolder.setText(R.id.tv_actual_end, risk.getActual_end());
    }
}
