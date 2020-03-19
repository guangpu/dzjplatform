package com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity.LeaderCheck;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  领导带班检查适配器
 */
public class LeaderCheckAdapter extends BaseQuickAdapter<LeaderCheck, BaseViewHolder> {

    public LeaderCheckAdapter(@Nullable List<LeaderCheck> data) {
        super(R.layout.item_leader_check, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, LeaderCheck leaderCheck) {
        baseViewHolder.setText(R.id.tv_project_name, leaderCheck.getProject_name());
        baseViewHolder.setText(R.id.tv_checked_unit, "");
        baseViewHolder.setText(R.id.tv_leader_name, leaderCheck.getClass_leader());
        baseViewHolder.setText(R.id.tv_project_manager, leaderCheck.getProject_manager());
        baseViewHolder.setText(R.id.tv_check_date, leaderCheck.getInspection_date());
        baseViewHolder.setText(R.id.tv_input_date, leaderCheck.getRectificat_date());
    }
}
