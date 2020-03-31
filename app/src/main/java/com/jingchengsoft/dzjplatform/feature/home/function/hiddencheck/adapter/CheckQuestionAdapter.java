package com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity.CheckQuestion;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author : wgp
 * time   :  2020/3/20
 * desc   :  检查问题adapter
 */
public class CheckQuestionAdapter  extends BaseQuickAdapter<CheckQuestion, BaseViewHolder> {
    public CheckQuestionAdapter(@Nullable List<CheckQuestion> data) {
        super(R.layout.item_check_question, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, CheckQuestion checkQuestion) {
        baseViewHolder.setText(R.id.tv_problem_description, checkQuestion.getProblem_description());
        baseViewHolder.setText(R.id.tv_rectification_requirements, checkQuestion.getRectification_requirements());
        baseViewHolder.setText(R.id.tv_rectification_person, checkQuestion.getRectification_person());
        baseViewHolder.setText(R.id.tv_complete_date, checkQuestion.getComplete_date());
        baseViewHolder.setText(R.id.tv_remark, checkQuestion.getRemark());
    }
}
