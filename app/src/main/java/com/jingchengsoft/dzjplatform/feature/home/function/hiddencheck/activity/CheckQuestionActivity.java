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
import com.jingchengsoft.dzjplatform.feature.home.function.specialwork.adapter.SpecialWorkDetailAdapter;
import com.jingchengsoft.dzjplatform.feature.home.function.specialwork.entity.SpecialWorkDetail;
import com.jingchengsoft.dzjplatform.feature.home.function.specialwork.utils.SpecialWorkHttpUtils;
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
    public static void start(String personId) {
        Bundle bundle = new Bundle();
        bundle.putString("personId", personId);
        ActivityUtils.startActivity(bundle, CheckQuestionActivity.class);
    }

    private String personId = "";
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
        personId = bundle.getString("personId");
        LogUtils.i("人员ID:"+personId);
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
        List<CheckQuestion> dataList = new ArrayList<>();

        CheckQuestion checkQuestion = new CheckQuestion();
        checkQuestion.setDesc("安全隐患问题");
        checkQuestion.setRectifyMethod("消除隐患");
        checkQuestion.setFinishDate("2020-6-15");
        checkQuestion.setRectifyPeople("张经理");
        checkQuestion.setRemark("备注");

        CheckQuestion checkQuestion1 = new CheckQuestion();
        checkQuestion1.setDesc("消防设备问题");
        checkQuestion1.setRectifyMethod("消除隐患");
        checkQuestion1.setFinishDate("2020-6-15");
        checkQuestion1.setRectifyPeople("张经理");
        checkQuestion1.setRemark("备注");

        dataList.add(checkQuestion);
        dataList.add(checkQuestion1);
        adapter.setNewData(dataList);
        adapter.notifyDataSetChanged();
    }
}
