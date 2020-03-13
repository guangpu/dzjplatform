package com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity.SafeCheck;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  安全专项检查adapter
 */
public class SafeCheckAdapter extends BaseQuickAdapter<SafeCheck, BaseViewHolder> {

    public SafeCheckAdapter(@Nullable List<SafeCheck> data) {
        super(R.layout.item_safe_check, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, SafeCheck safeCheck) {
        baseViewHolder.setText(R.id.tv_project_name, safeCheck.getProjectName());
        baseViewHolder.setText(R.id.tv_check_date, safeCheck.getCheckDate());
    }
}
