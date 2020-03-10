package com.jingchengsoft.dzjplatform.feature.home.function;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyFragment;
import com.jingchengsoft.dzjplatform.feature.home.HomeActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author : wgp
 * time   :  2020/3/10
 * desc   :
 */
public class FunctionFragment extends MyFragment<HomeActivity> {

    private FunctionAdapter adapter;
    private List<ItemFunctionEntity> dataList = new ArrayList<>();

    public static FunctionFragment newInstance() {
        return new FunctionFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_function;
    }

    @BindView(R.id.rv_home_function)
    RecyclerView rv_home_function;

    @Override
    protected void initView() {

    }

    @Override
    protected void initAdapter() {
        adapter = new FunctionAdapter(dataList, getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                ItemFunctionEntity item = dataList.get(position);
                int size;
                if(item.getType() == ItemFunctionEntity.Type.TypeTittle) {
                    size = 4;
                } else if(item.getType() == ItemFunctionEntity.Type.TypeFunction) {
                    size = 1;
                } else {
                    size = 0;
                }
                return size;
            }
        });
        rv_home_function.setLayoutManager(gridLayoutManager);
        rv_home_function.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        dataList.addAll(getSimulatedData());
        adapter.notifyDataSetChanged();
    }

    private List<ItemFunctionEntity> getSimulatedData() {
        List<ItemFunctionEntity> dataList = new ArrayList<>();

        ItemFunctionEntity item1 = new ItemFunctionEntity();
        item1.setType(ItemFunctionEntity.Type.TypeTittle);
        item1.setTitle("我的工作");
        dataList.add(item1);

        ItemFunctionEntity item2 = new ItemFunctionEntity();
        item2.setType(ItemFunctionEntity.Type.TypeFunction);
        item2.setName("设备管理");
        item2.setIcon(R.drawable.ic_f_three);
        dataList.add(item2);

        return dataList;
    }
}
