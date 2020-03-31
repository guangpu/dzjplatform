package com.jingchengsoft.dzjplatform.feature.home.function.devicemanage.activity;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.ActivityUtils;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.devicemanage.adapter.InspectionAdapter;
import com.jingchengsoft.dzjplatform.feature.home.function.devicemanage.entity.Inspection;
import com.jingchengsoft.dzjplatform.feature.home.function.devicemanage.utils.DeviceManageHttpUtils;
import com.jingchengsoft.dzjplatform.http.ApiResponse;
import com.jingchengsoft.dzjplatform.http.CommonException;
import com.jingchengsoft.dzjplatform.http.PretreatmentCallback;
import com.jingchengsoft.dzjplatform.ui.widget.CommonSearch;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :  设备管理
 */
public class DeviceManageActivity extends MyActivity {
    public static void start() {
        ActivityUtils.startActivity(DeviceManageActivity.class);
    }

    private List<Inspection> dataList = new ArrayList<>();
    private InspectionAdapter adapter;
    private int page = 0;
    private List<String> searchItem = new ArrayList<>();
    private String searchValue = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_device_manage;
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
        adapter = new InspectionAdapter(dataList);
        rv_check.setLayoutManager(linearLayoutManager);
        rv_check.setAdapter(adapter);
    }

    @Override
    public void onRightClick(View v) {
        InspectionAddActivity.start();
    }

    private void getListData(String searchValue, int page) {
        DeviceManageHttpUtils.getInspectionList(searchValue, page * 10, 10, new PretreatmentCallback<String>() {
            @Override
            public void onResponse(@NonNull ApiResponse response) {
                if(response.getData() != null) {
                    List<Inspection> list= JSON.parseArray(response.getData(), Inspection.class);
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
