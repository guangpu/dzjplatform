package com.jingchengsoft.dzjplatform.feature.home.message;

import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyFragment;
import com.jingchengsoft.dzjplatform.feature.home.HomeActivity;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :
 */
public class MessageFragment extends MyFragment<HomeActivity> {

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean statusBarDarkFont() {
        return false;
    }
}
