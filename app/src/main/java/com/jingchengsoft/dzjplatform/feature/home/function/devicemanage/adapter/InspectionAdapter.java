package com.jingchengsoft.dzjplatform.feature.home.function.devicemanage.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.feature.home.function.devicemanage.entity.Inspection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  设备巡检适配器
 */
public class InspectionAdapter extends BaseQuickAdapter<Inspection, BaseViewHolder> {

    public InspectionAdapter(@Nullable List<Inspection> data) {
        super(R.layout.item_inspection, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Inspection inspection) {
        baseViewHolder.setText(R.id.tv_inspection_name, inspection.getContent());
        baseViewHolder.setText(R.id.tv_write_people, inspection.getName());
        baseViewHolder.setText(R.id.tv_phone_num, inspection.getMobile());
        baseViewHolder.setText(R.id.tv_write_date, inspection.getCreate_time());
    }
}
