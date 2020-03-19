package com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.fragment;

import androidx.recyclerview.widget.RecyclerView;

import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyFragment;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.activity.HiddenCheckActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.adapter.SafeCheckAdapter;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.adapter.SafeLogAdapter;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity.SafeCheck;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity.SafeLog;
import com.jingchengsoft.dzjplatform.ui.widget.CommonSearch;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  安全监督日志
 */
public class SafeLogFragment extends MyFragment<HiddenCheckActivity> {
    private List<SafeLog> dataList = new ArrayList<>();
    private SafeLogAdapter adapter;
    private int page = 0;
    private List<String> searchItem = new ArrayList<>();
    private String searchValue = "";

    public static SafeLogFragment newInstance() {
        return new SafeLogFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hidden_check;
    }

    @BindView(R.id.srl_check)
    SmartRefreshLayout srl_check;
    @BindView(R.id.rv_check)
    RecyclerView rv_check;
    @BindView(R.id.common_search)
    CommonSearch commonSearch;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        getListData(searchValue, page);
        searchItem.add("名称");
        commonSearch.setSearchParam(searchItem);
    }

    @Override
    protected void initAdapter() {
        adapter = new SafeLogAdapter(dataList);
        rv_check.setLayoutManager(linearLayoutManager);
        rv_check.setAdapter(adapter);
    }

    private void getListData(String searchValue, int page) {
        List<SafeLog> dataList = new ArrayList<>();
        SafeLog safeLog = new SafeLog();
        safeLog.setProjectName("项目0319");
        safeLog.setWeatherStatus("晴");
        safeLog.setTstdStatus("正常");
        safeLog.setWritePeople("刘世杰");
        safeLog.setWriteDate("2020-05-08");

        SafeLog safeLog1 = new SafeLog();
        safeLog1.setProjectName("项目0318");
        safeLog1.setWeatherStatus("雨");
        safeLog1.setTstdStatus("停电");
        safeLog1.setWritePeople("刘世杰");
        safeLog1.setWriteDate("2020-05-08");

        dataList.add(safeLog);
        dataList.add(safeLog1);
        adapter.setNewData(dataList);
        adapter.notifyDataSetChanged();
    }

}
