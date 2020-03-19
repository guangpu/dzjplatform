package com.jingchengsoft.dzjplatform.feature.home.function.emergency.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.feature.home.function.emergency.entity.PractisePlan;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity.LeaderCheck;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  演练记录适配器
 */
public class PractisePlanAdapter extends BaseQuickAdapter<PractisePlan, BaseViewHolder> {

    public PractisePlanAdapter(@Nullable List<PractisePlan> data) {
        super(R.layout.item_practise_plan, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, PractisePlan practisePlan) {
        baseViewHolder.setText(R.id.tv_unit, practisePlan.getComp_name());
        baseViewHolder.setText(R.id.tv_plan_name, practisePlan.getName());
        baseViewHolder.setText(R.id.tv_attach, practisePlan.getFile_real_name());
    }
}
