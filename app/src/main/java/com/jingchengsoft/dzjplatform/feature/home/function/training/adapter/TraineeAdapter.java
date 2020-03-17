package com.jingchengsoft.dzjplatform.feature.home.function.training.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.feature.home.function.specialwork.entity.SpecialWork;
import com.jingchengsoft.dzjplatform.feature.home.function.training.entity.Trainee;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  培训人员adapter
 */
public class TraineeAdapter extends BaseQuickAdapter<Trainee, BaseViewHolder> {

    public TraineeAdapter(@Nullable List<Trainee> data) {
        super(R.layout.item_trainee, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Trainee trainee) {
        baseViewHolder.setText(R.id.tv_name, trainee.getName());
        baseViewHolder.setText(R.id.tv_id_card, trainee.getId_card());
        baseViewHolder.setText(R.id.tv_score1, trainee.getScore1() + "分");
        baseViewHolder.setText(R.id.tv_score2, trainee.getScore2() + "分");
        baseViewHolder.setText(R.id.tv_score3, trainee.getScore3() + "分");
    }
}
