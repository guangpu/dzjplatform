package com.jingchengsoft.dzjplatform.feature.home.function.specialwork.activity;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.ActivityUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyActivity;
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
 * desc   :  特种作业人员管理
 */
public class SpecialWorkActivity extends MyActivity {
    public static void start() {
        ActivityUtils.startActivity(SpecialWorkActivity.class);
    }

    private List<SpecialWork> dataList = new ArrayList<>();
    private SpecialWorkAdapter adapter;
    private int page = 0;
    private List<String> searchItem = new ArrayList<>();
    private String searchValue = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_special_work;
    }
    @BindView(R.id.srl_special_work)
    SmartRefreshLayout srl_special_work;
    @BindView(R.id.rv_special_work)
    RecyclerView rv_special_work;
    @BindView(R.id.common_search)
    CommonSearch commonSearch;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        getListData(searchValue, page);
        searchItem.add("姓名");
        commonSearch.setSearchParam(searchItem);
    }

    @Override
    protected void initAdapter() {
        adapter = new SpecialWorkAdapter(dataList);
        rv_special_work.setLayoutManager(linearLayoutManager);
        rv_special_work.setAdapter(adapter);
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

        srl_special_work.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                getListData(searchValue, page);
            }
        });

        srl_special_work.setOnLoadMoreListener(new OnLoadMoreListener() {
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
                    SpecialWorkDetailActivity.start(dataList.get(position).getId());
                }
            }
        });
    }


    private void getListData(String searchValue, int page) {
        SpecialWorkHttpUtils.getSpecialWorkList(searchValue, page*10, 10, new PretreatmentCallback<String>() {
            @Override
            public void onResponse(@NonNull ApiResponse response) {
                if(response.getData() != null) {
                    List<SpecialWork> list= JSON.parseArray(response.getData(), SpecialWork.class);
                    if(page == 0) {
                        srl_special_work.finishRefresh();
                        dataList.clear();
                    }
                    srl_special_work.finishLoadMore();
                    dataList.addAll(list);
                    adapter.setNewData(dataList);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onException(CommonException e) {
                if(page == 0) {
                    srl_special_work.finishRefresh();
                }
                srl_special_work.finishLoadMore();
                toast(e.getException().getMessage());
            }
        });
    }
}
