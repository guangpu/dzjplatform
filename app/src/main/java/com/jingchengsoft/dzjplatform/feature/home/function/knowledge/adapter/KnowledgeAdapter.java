package com.jingchengsoft.dzjplatform.feature.home.function.knowledge.adapter;

import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity.SafeCheck;
import com.jingchengsoft.dzjplatform.feature.home.function.knowledge.entity.Knowledge;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  知识资源adapter
 */
public class KnowledgeAdapter extends BaseQuickAdapter<Knowledge, BaseViewHolder> {

    public KnowledgeAdapter(@Nullable List<Knowledge> data) {
        super(R.layout.item_knowledge, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Knowledge knowledge) {
        baseViewHolder.setText(R.id.tv_file_real_name, knowledge.getFile_real_name());
        baseViewHolder.setText(R.id.tv_file_size, ConvertUtils.byte2FitMemorySize(Long.parseLong(String.valueOf(knowledge.getFile_size()))));
        baseViewHolder.setText(R.id.tv_update_time, convertDate(knowledge.getCreate_time()));
    }

    /**
     * 时间格式转换
     * @param date
     * @return
     */
    private String convertDate(String date) {
        if(date!=null && date.length() > 8) {
            date = date.substring(0,4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
        }
        return date;
    }
}
