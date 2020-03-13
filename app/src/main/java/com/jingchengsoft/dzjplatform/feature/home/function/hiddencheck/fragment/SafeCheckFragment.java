package com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.fragment;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyFragment;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.activity.HiddenCheckActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.adapter.SafeCheckAdapter;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity.SafeCheck;
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
 * desc   :  安全专项检查
 */
public class SafeCheckFragment extends MyFragment<HiddenCheckActivity> {
    private SafeCheckAdapter adapter;
    private List<SafeCheck> dataList = new ArrayList<>();

    public static SafeCheckFragment newInstance() {
        return new SafeCheckFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hidden_check;
    }

    @BindView(R.id.srl_check)
    SmartRefreshLayout srl_check;
    @BindView(R.id.rv_check)
    RecyclerView rv_check;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initAdapter() {
        adapter = new SafeCheckAdapter(dataList);
        rv_check.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        srl_check.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });

        srl_check.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }
        });
    }

    /**
     * 刷新列表
     */
    private void refresh() {

    }

    /**
     * 加载更多
     */
    private void loadMore() {

    }

}
