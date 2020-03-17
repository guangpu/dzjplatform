package com.jingchengsoft.dzjplatform.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatButton;

import com.hjq.widget.view.ClearEditText;
import com.jingchengsoft.dzjplatform.R;

import org.angmarch.views.NiceSpinner;

import java.util.List;

/**
 * author : wgp
 * time   :  2020/3/16
 * desc   :  通用搜索
 */
public class CommonSearch extends RelativeLayout {
    private Context mContext;
    private View mView;
    private AppCompatButton btnSearch;
    private NiceSpinner niceSpinner;
    private ClearEditText clearEditText;

    public CommonSearch(Context context) {
        this(context,null);
    }

    public CommonSearch(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonSearch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.common_search, this, true);
        btnSearch = mView.findViewById(R.id.btn_search);
        niceSpinner = mView.findViewById(R.id.ns_item);
        clearEditText = mView.findViewById(R.id.cet_content);
    }

    /**
     * 设置查询项
     * @param items
     */
    public void setSearchParam(List<String> items) {
        niceSpinner.attachDataSource(items);
    }

    /**
     * 查询按钮监听
     * @param listener
     */
    public void setSearchListener(OnClickListener listener) {
        btnSearch.setOnClickListener(listener);
    }

    /**
     * 获取输入查询值
     * @return
     */
    public String getSearchContent() {
        return clearEditText.getText().toString();
    }
}
