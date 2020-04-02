package com.jingchengsoft.dzjplatform.feature.home.function.riskmanage.activity;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.ActivityUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.riskmanage.adapter.RiskAdapter;
import com.jingchengsoft.dzjplatform.feature.home.function.riskmanage.entity.Risk;
import com.jingchengsoft.dzjplatform.feature.home.function.riskmanage.utils.RiskManageHttpUtils;
import com.jingchengsoft.dzjplatform.feature.home.function.specialwork.activity.SpecialWorkDetailActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.specialwork.adapter.SpecialWorkAdapter;
import com.jingchengsoft.dzjplatform.feature.home.function.specialwork.entity.SpecialWork;
import com.jingchengsoft.dzjplatform.feature.home.function.specialwork.utils.SpecialWorkHttpUtils;
import com.jingchengsoft.dzjplatform.feature.home.function.training.entity.Trainee;
import com.jingchengsoft.dzjplatform.feature.home.function.training.utils.TrainingHttpUtils;
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
 * desc   :  风险管控
 */
public class RiskManageActivity extends MyActivity {
    public static void start() {
        ActivityUtils.startActivity(RiskManageActivity.class);
    }

    private List<Risk> dataList = new ArrayList<>();
    private RiskAdapter adapter;
    private int page = 0;
    private List<String> searchItem = new ArrayList<>();
    private String searchValue = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_risk_manage;
    }

    @BindView(R.id.srl_common)
    SmartRefreshLayout srl_common;
    @BindView(R.id.rv_common)
    RecyclerView rv_common;
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
        adapter = new RiskAdapter(dataList);
        rv_common.setLayoutManager(linearLayoutManager);
        rv_common.setAdapter(adapter);
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

        srl_common.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                getListData(searchValue, page);
            }
        });

        srl_common.setOnLoadMoreListener(new OnLoadMoreListener() {
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
                if (view.getId() == R.id.btn_choose) {
                    RiskManageDetailActivity.start(dataList.get(position).getId(), dataList.get(position));
                }
            }
        });
    }


    private void getListData(String searchValue, int page) {
        RiskManageHttpUtils.getDangerList(searchValue, page*10, 10, new PretreatmentCallback<String>() {
            @Override
            public void onResponse(@NonNull ApiResponse response) {
                if(response.getData() != null) {
                    List<Risk> list= JSON.parseArray(response.getData(), Risk.class);
                    if(page == 0) {
                        srl_common.finishRefresh();
                        dataList.clear();
                    }
                    srl_common.finishLoadMore();
                    dataList.addAll(list);
                    adapter.setNewData(dataList);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onException(CommonException e) {
                if(page == 0) {
                    srl_common.finishRefresh();
                }
                srl_common.finishLoadMore();
                toast(e.getException().getMessage());
            }
        });
    }
}
