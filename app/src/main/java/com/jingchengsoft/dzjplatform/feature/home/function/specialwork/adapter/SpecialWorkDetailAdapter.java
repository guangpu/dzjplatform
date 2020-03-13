package com.jingchengsoft.dzjplatform.feature.home.function.specialwork.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.feature.home.function.specialwork.entity.SpecialWork;
import com.jingchengsoft.dzjplatform.feature.home.function.specialwork.entity.SpecialWorkDetail;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  特种作业人员adapter
 */
public class SpecialWorkDetailAdapter extends BaseQuickAdapter<SpecialWorkDetail, BaseViewHolder> {

    public SpecialWorkDetailAdapter(@Nullable List<SpecialWorkDetail> data) {
        super(R.layout.item_specail_work_detail, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, SpecialWorkDetail SpecialWorkDetail) {
        baseViewHolder.setText(R.id.tv_person_name, SpecialWorkDetail.getName());
        baseViewHolder.setText(R.id.tv_work_type, SpecialWorkDetail.getWork_type());
        baseViewHolder.setText(R.id.tv_first_get_cert_date, SpecialWorkDetail.getFirst_get_cert_date());
        baseViewHolder.setText(R.id.tv_special_cert_no, SpecialWorkDetail.getSpecial_cert_no());
        baseViewHolder.setText(R.id.tv_recheck_date, SpecialWorkDetail.getRecheck_date());
    }
}
