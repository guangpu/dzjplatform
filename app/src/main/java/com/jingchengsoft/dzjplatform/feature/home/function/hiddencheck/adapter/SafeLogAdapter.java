package com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity.SafeLog;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author : wgp
 * time   :  2020/3/13
 * desc   :  安全监督日志adapter
 */
public class SafeLogAdapter extends BaseQuickAdapter<SafeLog, BaseViewHolder> {
    public SafeLogAdapter(@Nullable List<SafeLog> data) {
        super(R.layout.item_safe_log, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, SafeLog safeLog) {
        baseViewHolder.setText(R.id.tv_project_name, safeLog.getProject_name());
        baseViewHolder.setText(R.id.tv_weather_status, safeLog.getWeather_condition());
        baseViewHolder.setText(R.id.tv_tstd_status, safeLog.getWaterpower_outage_name());
        baseViewHolder.setText(R.id.tv_write_people, safeLog.getCreater_id());
        baseViewHolder.setText(R.id.tv_write_date, safeLog.getCreate_time());
    }
}
