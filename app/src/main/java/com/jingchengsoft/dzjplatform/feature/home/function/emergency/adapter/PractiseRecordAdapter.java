package com.jingchengsoft.dzjplatform.feature.home.function.emergency.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.feature.home.function.emergency.entity.PractiseRecord;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  安全专项检查adapter
 */
public class PractiseRecordAdapter extends BaseQuickAdapter<PractiseRecord, BaseViewHolder> {

    public PractiseRecordAdapter(@Nullable List<PractiseRecord> data) {
        super(R.layout.item_practise_record, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, PractiseRecord practiseRecord) {
        baseViewHolder.setText(R.id.tv_unit, practiseRecord.getComp_name());
        baseViewHolder.setText(R.id.tv_practise_name, practiseRecord.getName());
        baseViewHolder.setText(R.id.tv_practise_date, practiseRecord.getDrill_date());
        baseViewHolder.setText(R.id.tv_join_number, String.valueOf(practiseRecord.getAttend_people()));
        baseViewHolder.setText(R.id.tv_total_number, String.valueOf(practiseRecord.getTotal_people()));
    }
}
