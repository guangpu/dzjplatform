package com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.fragment;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyFragment;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.activity.HiddenCheckActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.adapter.SafeCheckAdapter;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.entity.SafeCheck;
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
 * desc   :  安全专项检查
 */
public class SafeCheckFragment extends MyFragment<HiddenCheckActivity> {
    private List<SafeCheck> dataList = new ArrayList<>();
    private SafeCheckAdapter adapter;
    private int page = 0;
    private List<String> searchItem = new ArrayList<>();
    private String searchValue = "";

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
        adapter = new SafeCheckAdapter(dataList);
        rv_check.setLayoutManager(linearLayoutManager);
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

    private void getListData(String searchValue, int page) {
        List<SafeCheck> dataList = new ArrayList<>();
        SafeCheck safeCheck = new SafeCheck();
        safeCheck.setProjectName("安全检查0223");
        safeCheck.setCheckClassify("防火检查");
        safeCheck.setInputPeople("王丽娟");
        safeCheck.setWritePeople("李凯丽");
        safeCheck.setCheckDate("2020-4-6");
        safeCheck.setInputDate("2020-4-12");

        SafeCheck safeCheck1 = new SafeCheck();
        safeCheck1.setProjectName("安全检查0225");
        safeCheck1.setCheckClassify("电器检查");
        safeCheck1.setInputPeople("王娟");
        safeCheck1.setWritePeople("李丽");
        safeCheck1.setCheckDate("2020-4-16");
        safeCheck1.setInputDate("2020-4-22");

        dataList.add(safeCheck);
        dataList.add(safeCheck1);
        adapter.setNewData(dataList);
        adapter.notifyDataSetChanged();
    }

}
