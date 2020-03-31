package com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.adapter.CheckQuestionAdapter;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity.CheckQuestion;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.utils.HiddenCheckHttpUtils;
import com.jingchengsoft.dzjplatform.http.ApiResponse;
import com.jingchengsoft.dzjplatform.http.CommonException;
import com.jingchengsoft.dzjplatform.http.PretreatmentCallback;
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
 * desc   :  检查问题列表
 */
public class CheckQuestionActivity extends MyActivity {
    public static void start(String inspectionId) {
        Bundle bundle = new Bundle();
        bundle.putString("inspectionId", inspectionId);
        ActivityUtils.startActivity(bundle, CheckQuestionActivity.class);
    }

    private String inspectionId = "";
    private List<CheckQuestion> dataList = new ArrayList<>();
    private CheckQuestionAdapter adapter;
    private int page = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_check_question;
    }
    @BindView(R.id.srl_special_work)
    SmartRefreshLayout srl_special_work;
    @BindView(R.id.rv_special_work)
    RecyclerView rv_special_work;

    @Override
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        inspectionId = bundle.getString("inspectionId");
        LogUtils.i("检查ID:"+inspectionId);
    }

    @Override
    protected void initData() {
        getListData(page);
    }

    @Override
    protected void initAdapter() {
        adapter = new CheckQuestionAdapter(dataList);
        rv_special_work.setLayoutManager(linearLayoutManager);
        rv_special_work.setAdapter(adapter);
    }

    @Override
    public void onRightClick(View v) {
        //HiddenCheckQuestionAddActivity.start();
    }

    @Override
    protected void initListener() {
        srl_special_work.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                getListData(page);
            }
        });

        srl_special_work.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getListData(page);
            }
        });

        adapter.addChildClickViewIds(R.id.btn_choose);
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
//                if(view.getId() == R.id.btn_choose) {
//                    toast(dataList.get(position).getName());
//                }
            }
        });
    }


    private void getListData(int page) {
        HiddenCheckHttpUtils.getCheckQuestionList(page * 10, 10, inspectionId, "", "", new PretreatmentCallback<String>() {
            @Override
            public void onResponse(@NonNull ApiResponse response) {
                if(response.getData() != null) {
                    List<CheckQuestion> list= JSON.parseArray(response.getData(), CheckQuestion.class);
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
