package com.jingchengsoft.dzjplatform.feature.home.function.devicemanage.activity;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.devicemanage.adapter.InspectionAdapter;
import com.jingchengsoft.dzjplatform.feature.home.function.devicemanage.entity.Inspection;
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
        List<Inspection> dataList = new ArrayList<>();

        Inspection inspection = new Inspection();
        inspection.setInspectionName("钻机巡检记录");
        inspection.setWritePeople("张杰");
        inspection.setPhoneNum("15625856956");
        inspection.setWriteDate("2020-05-11");

        Inspection inspection1 = new Inspection();
        inspection1.setInspectionName("消防设施巡检记录");
        inspection1.setWritePeople("张杰");
        inspection1.setPhoneNum("15625856956");
        inspection1.setWriteDate("2020-05-11");

        dataList.add(inspection);
        dataList.add(inspection1);
        adapter.setNewData(dataList);
        adapter.notifyDataSetChanged();
    }
}
