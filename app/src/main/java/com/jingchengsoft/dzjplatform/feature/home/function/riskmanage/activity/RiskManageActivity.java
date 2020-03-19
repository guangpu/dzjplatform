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
import com.jingchengsoft.dzjplatform.feature.home.function.specialwork.activity.SpecialWorkDetailActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.specialwork.adapter.SpecialWorkAdapter;
import com.jingchengsoft.dzjplatform.feature.home.function.specialwork.entity.SpecialWork;
import com.jingchengsoft.dzjplatform.feature.home.function.specialwork.utils.SpecialWorkHttpUtils;
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
                    //SpecialWorkDetailActivity.start(dataList.get(position).getId());
                }
            }
        });
    }


    private void getListData(String searchValue, int page) {
        List<Risk> dataList = new ArrayList<>();
        Risk risk = new Risk();
        risk.setProjectName("电源异常");
        risk.setReportDate("2020-03-18");
        risk.setReportPeople("李俊凯");

        Risk risk1 = new Risk();
        risk1.setProjectName("粉尘过量");
        risk1.setReportDate("2020-03-20");
        risk1.setReportPeople("李俊凯");

        dataList.add(risk);
        dataList.add(risk1);
        adapter.setNewData(dataList);
        adapter.notifyDataSetChanged();
    }
}