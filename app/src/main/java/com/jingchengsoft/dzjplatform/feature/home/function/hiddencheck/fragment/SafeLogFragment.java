package com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyFragment;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.activity.HiddenCheckActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.activity.SafeCheckDetailActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.activity.SafeLogDetailActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.adapter.SafeCheckAdapter;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.adapter.SafeLogAdapter;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity.SafeCheck;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity.SafeLog;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.utils.HiddenCheckHttpUtils;
import com.jingchengsoft.dzjplatform.http.ApiResponse;
import com.jingchengsoft.dzjplatform.http.CommonException;
import com.jingchengsoft.dzjplatform.http.PretreatmentCallback;
import com.jingchengsoft.dzjplatform.ui.widget.CommonSearch;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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

    @Override
    protected void initListener() {
        commonSearch.setSearchListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchValue = commonSearch.getSearchContent();
                page = 0;
                getListData(searchValue, page);
            }
        });

        srl_check.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                getListData(searchValue, page);
            }
        });

        srl_check.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getListData(searchValue, page);
            }
        });

        adapter.addChildClickViewIds(R.id.btn_choose);
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                SafeLogDetailActivity.start(dataList.get(position).getId());
                if(view.getId() == R.id.btn_choose) {
                    SafeLogDetailActivity.start(dataList.get(position).getId());
                }
            }
        });
    }

    private void getListData(String searchValue, int page) {
        HiddenCheckHttpUtils.getCheckList(searchValue, page * 10, 10, "4", new PretreatmentCallback<String>() {
            @Override
            public void onResponse(@NonNull ApiResponse response) {
                if(response.getData() != null) {
                    List<SafeLog> list= JSON.parseArray(response.getData(), SafeLog.class);
                    if(page == 0) {
                        srl_check.finishRefresh();
                        dataList.clear();
                    }
                    srl_check.finishLoadMore();
                    dataList.addAll(list);
                    adapter.setNewData(dataList);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onException(CommonException e) {
                if(page == 0) {
                    srl_check.finishRefresh();
                }
                srl_check.finishLoadMore();
                toast(e.getException().getMessage());
            }
        });
    }

}
