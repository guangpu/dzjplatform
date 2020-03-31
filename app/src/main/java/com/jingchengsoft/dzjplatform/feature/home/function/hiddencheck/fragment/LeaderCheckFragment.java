package com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyFragment;
import com.jingchengsoft.dzjplatform.feature.home.function.emergency.entity.PractiseRecord;
import com.jingchengsoft.dzjplatform.feature.home.function.emergency.utils.EmergencyHttpUtils;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.activity.CheckQuestionActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.activity.HiddenCheckActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.activity.LeaderCheckDetailActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.adapter.LeaderCheckAdapter;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity.LeaderCheck;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.utils.HiddenCheckHttpUtils;
import com.jingchengsoft.dzjplatform.feature.home.function.riskmanage.entity.Risk;
import com.jingchengsoft.dzjplatform.feature.home.function.specialwork.adapter.SpecialWorkAdapter;
import com.jingchengsoft.dzjplatform.feature.home.function.specialwork.entity.SpecialWork;
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
 * desc   :  领导带班检查
 */
public class LeaderCheckFragment extends MyFragment<HiddenCheckActivity> {

    private List<LeaderCheck> dataList = new ArrayList<>();
    private LeaderCheckAdapter adapter;
    private int page = 0;
    private List<String> searchItem = new ArrayList<>();
    private String searchValue = "";

    public static LeaderCheckFragment newInstance() {
        return new LeaderCheckFragment();
    }

    @BindView(R.id.srl_check)
    SmartRefreshLayout srl_check;
    @BindView(R.id.rv_check)
    RecyclerView rv_check;
    @BindView(R.id.common_search)
    CommonSearch commonSearch;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hidden_check;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        getListData(searchValue, page);
        searchItem.add("关键字");
        commonSearch.setSearchParam(searchItem);
    }

    @Override
    protected void initAdapter() {
        adapter = new LeaderCheckAdapter(dataList);
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
                LeaderCheckDetailActivity.start(dataList.get(position).getId());
                if(view.getId() == R.id.btn_choose) {
                    LeaderCheckDetailActivity.start(dataList.get(position).getId());
                }
            }
        });
    }

    private void getListData(String searchValue, int page) {
//        List<LeaderCheck> dataList = new ArrayList<>();
//
//        LeaderCheck leaderCheck = new LeaderCheck();
//        leaderCheck.setProjectName("受检项目");
//        leaderCheck.setLeaderName("李凯凯");
//        leaderCheck.setProjectManager("黄利润");
//        leaderCheck.setUnit("山东黄金");
//        leaderCheck.setInputDate("2020-9-8");
//        leaderCheck.setCheckDate("2019-6-9");
//
//        LeaderCheck leaderCheck1 = new LeaderCheck();
//        leaderCheck1.setProjectName("受检项目2019");
//        leaderCheck1.setLeaderName("李凯凯");
//        leaderCheck1.setProjectManager("黄利润");
//        leaderCheck1.setUnit("山东黄金");
//        leaderCheck1.setInputDate("2020-9-8");
//        leaderCheck1.setCheckDate("2019-6-9");
//
//        dataList.add(leaderCheck);
//        dataList.add(leaderCheck1);
//        adapter.setNewData(dataList);
//        adapter.notifyDataSetChanged();
        HiddenCheckHttpUtils.getCheckList(searchValue, page * 10, 10, "1", new PretreatmentCallback<String>() {
            @Override
            public void onResponse(@NonNull ApiResponse response) {
                if(response.getData() != null) {
                    List<LeaderCheck> list= JSON.parseArray(response.getData(), LeaderCheck.class);
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
