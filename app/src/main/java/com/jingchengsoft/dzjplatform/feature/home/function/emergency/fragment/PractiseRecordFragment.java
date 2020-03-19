package com.jingchengsoft.dzjplatform.feature.home.function.emergency.fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyFragment;
import com.jingchengsoft.dzjplatform.feature.home.function.emergency.adapter.PractiseRecordAdapter;
import com.jingchengsoft.dzjplatform.feature.home.function.emergency.entity.PractisePlan;
import com.jingchengsoft.dzjplatform.feature.home.function.emergency.entity.PractiseRecord;
import com.jingchengsoft.dzjplatform.feature.home.function.emergency.utils.EmergencyHttpUtils;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.activity.HiddenCheckActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.adapter.SafeCheckAdapter;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity.SafeCheck;
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
 * desc   :  演练记录
 */
public class PractiseRecordFragment extends MyFragment<HiddenCheckActivity> {
    private List<PractiseRecord> dataList = new ArrayList<>();
    private PractiseRecordAdapter adapter;
    private int page = 0;
    private List<String> searchItem = new ArrayList<>();
    private String searchValue = "";

    public static PractiseRecordFragment newInstance() {
        return new PractiseRecordFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_practise;
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
        adapter = new PractiseRecordAdapter(dataList);
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
                if(view.getId() == R.id.btn_choose) {
                    //SpecialWorkDetailActivity.start(dataList.get(position).getId());
                }
            }
        });
    }

    private void getListData(String searchValue, int page) {
        EmergencyHttpUtils.getPractiseRecordList(searchValue, page * 10, 10, new PretreatmentCallback<String>() {
            @Override
            public void onResponse(@NonNull ApiResponse response) {
                if(response.getData() != null) {
                    List<PractiseRecord> list= JSON.parseArray(response.getData(), PractiseRecord.class);
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
