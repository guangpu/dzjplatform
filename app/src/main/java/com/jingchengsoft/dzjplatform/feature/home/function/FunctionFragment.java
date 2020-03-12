package com.jingchengsoft.dzjplatform.feature.home.function;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyFragment;
import com.jingchengsoft.dzjplatform.feature.home.HomeActivity;
import com.jingchengsoft.dzjplatform.feature.home.function.hiddencheck.activity.HiddenCheckActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
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
    protected void initListener() {
        adapter.setOnItemClickListener(new FunctionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, List<ItemFunctionEntity> dataBeanList) {
                switch (dataBeanList.get(position).getName()) {
                    case "隐患排查":
                        HiddenCheckActivity.start();
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        dataList.addAll(getSimulatedData());
        adapter.notifyDataSetChanged();
    }

    private List<ItemFunctionEntity> getSimulatedData() {
        List<ItemFunctionEntity> dataList = new ArrayList<>();

//        ItemFunctionEntity item1 = new ItemFunctionEntity();
//        item1.setType(ItemFunctionEntity.Type.TypeTittle);
//        item1.setTitle("我的工作");
//        dataList.add(item1);

        ItemFunctionEntity item2 = new ItemFunctionEntity();
        item2.setType(ItemFunctionEntity.Type.TypeFunction);
        item2.setName("风险管控");
        item2.setIcon(R.drawable.ic_f_bbs);
        dataList.add(item2);

        ItemFunctionEntity item3 = new ItemFunctionEntity();
        item3.setType(ItemFunctionEntity.Type.TypeFunction);
        item3.setName("隐患排查");
        item3.setIcon(R.drawable.ic_f_e_date);
        dataList.add(item3);

        ItemFunctionEntity item4 = new ItemFunctionEntity();
        item4.setType(ItemFunctionEntity.Type.TypeFunction);
        item4.setName("教育培训");
        item4.setIcon(R.drawable.ic_f_journal);
        dataList.add(item4);

        ItemFunctionEntity item5 = new ItemFunctionEntity();
        item5.setType(ItemFunctionEntity.Type.TypeFunction);
        item5.setName("设备管理");
        item5.setIcon(R.drawable.ic_f_e_date);
        dataList.add(item5);

        ItemFunctionEntity item6 = new ItemFunctionEntity();
        item6.setType(ItemFunctionEntity.Type.TypeFunction);
        item6.setName("特种作业");
        item6.setIcon(R.drawable.ic_f_p_date);
        dataList.add(item6);

        ItemFunctionEntity item7 = new ItemFunctionEntity();
        item7.setType(ItemFunctionEntity.Type.TypeFunction);
        item7.setName("应急管理");
        item7.setIcon(R.drawable.ic_f_continue);
        dataList.add(item7);

        ItemFunctionEntity item8 = new ItemFunctionEntity();
        item8.setType(ItemFunctionEntity.Type.TypeFunction);
        item8.setName("知识库");
        item8.setIcon(R.drawable.ic_f_journal);
        dataList.add(item8);

        ItemFunctionEntity item9 = new ItemFunctionEntity();
        item9.setType(ItemFunctionEntity.Type.TypeFunction);
        item9.setName("统计分析");
        item9.setIcon(R.drawable.ic_f_three);
        dataList.add(item9);

        return dataList;
    }
}
