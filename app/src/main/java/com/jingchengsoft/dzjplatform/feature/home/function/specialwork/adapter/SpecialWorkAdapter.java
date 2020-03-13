package com.jingchengsoft.dzjplatform.feature.home.function.specialwork.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity.SafeCheck;
import com.jingchengsoft.dzjplatform.feature.home.function.specialwork.entity.SpecialWork;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  特种作业人员adapter
 */
public class SpecialWorkAdapter extends BaseQuickAdapter<SpecialWork, BaseViewHolder> {

    public SpecialWorkAdapter(@Nullable List<SpecialWork> data) {
        super(R.layout.item_specail_work, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, SpecialWork specialWork) {
        baseViewHolder.setText(R.id.tv_person_name, specialWork.getName());
        baseViewHolder.setText(R.id.tv_birthday, specialWork.getBirth_date());
        baseViewHolder.setText(R.id.tv_work_date, specialWork.getBirth_date());
        baseViewHolder.setText(R.id.tv_department, specialWork.getDepartment());
        String sex = "";
        if(specialWork.getSex().equals("1")) {
            sex = "男";
            baseViewHolder.setBackgroundResource(R.id.iv_photo, R.drawable.ic_person_male);
        }
        if(specialWork.getSex().equals("2")) {
            sex = "女";
            baseViewHolder.setBackgroundResource(R.id.iv_photo, R.drawable.ic_person_female);
        }
        baseViewHolder.setText(R.id.tv_sex, sex);
    }
}
