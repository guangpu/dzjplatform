package com.jingchengsoft.dzjplatform.feature.home.function;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.jingchengsoft.dzjplatform.R;

import java.util.List;

/**
 * @author MaybeSix
 * @date 2019/11/6
 * @desc TODO.
 */
public class FunctionAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_TITTLE = 1;
    private static final int VIEW_TYPE_Function = 2;
    private LayoutInflater inflater;
    private Context mContext;
    private List<ItemFunctionEntity> mDataList;
    private OnItemClickListener onItemClickListener;

    public FunctionAdapter(List<ItemFunctionEntity> mDataList, Context context) {
        this.mDataList = mDataList;
        mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {

        if (mDataList.get(position).getType() == ItemFunctionEntity.Type.TypeTittle) {
            return VIEW_TYPE_TITTLE;
        } else if (mDataList.get(position).getType() == ItemFunctionEntity.Type.TypeFunction) {
            return VIEW_TYPE_Function;
        } else {
            return 0;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case VIEW_TYPE_TITTLE:
                viewHolder = new ViewHolderOne(inflater.inflate(R.layout.item_function_tittle, parent, false));
                break;
            case VIEW_TYPE_Function:
                viewHolder = new ViewHolderTwo(inflater.inflate(R.layout.item_function, parent, false));
                break;
            default:
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case VIEW_TYPE_TITTLE:
                ((ViewHolderOne) holder).title.setText(mDataList.get(position).getTitle());
                break;
            case VIEW_TYPE_Function:
                ((ViewHolderTwo) holder).title.setText(mDataList.get(position).getName());
                ((ViewHolderTwo) holder).icon.setImageDrawable(
                        mContext.getResources().getDrawable(mDataList.get(position).getIcon()));
                break;
            default:
                break;

        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    /**
     * 第一种布局类型ViewHolder
     */
    public class ViewHolderOne extends RecyclerView.ViewHolder {
        private TextView title;

        ViewHolderOne(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.text);
        }
    }

    /**
     * 第二种布局类型ViewHolder
     */
    public class ViewHolderTwo extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView title;

        ViewHolderTwo(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.item_function_image);
            title = itemView.findViewById(R.id.item_function_title);
            itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(getAdapterPosition(), mDataList);
                }
            });

        }
    }

    public interface OnItemClickListener {
        //RecyclerView的点击事件，将信息回调给view
        void onItemClick(int position, List<ItemFunctionEntity> dataBeanList);
    }

}
