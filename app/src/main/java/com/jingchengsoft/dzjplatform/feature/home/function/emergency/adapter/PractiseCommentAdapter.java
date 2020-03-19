package com.jingchengsoft.dzjplatform.feature.home.function.emergency.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.feature.home.function.emergency.entity.PractiseComment;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity.SafeLog;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author : wgp
 * time   :  2020/3/13
 * desc   :  演练评价adapter
 */
public class PractiseCommentAdapter extends BaseQuickAdapter<PractiseComment, BaseViewHolder> {
    public PractiseCommentAdapter(@Nullable List<PractiseComment> data) {
        super(R.layout.item_practise_comment, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, PractiseComment practiseComment) {
        baseViewHolder.setText(R.id.tv_unit, practiseComment.getComp_name());
        baseViewHolder.setText(R.id.tv_practise_name, practiseComment.getRecordname());
        baseViewHolder.setText(R.id.tv_attach, practiseComment.getFile_real_name());
    }
}
