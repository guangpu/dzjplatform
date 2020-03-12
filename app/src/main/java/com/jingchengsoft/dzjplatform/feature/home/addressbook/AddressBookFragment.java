package com.jingchengsoft.dzjplatform.feature.home.addressbook;

import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyFragment;
import com.jingchengsoft.dzjplatform.feature.home.HomeActivity;

/**
 * author : wgp
 * time   :  2020/3/11
 * desc   :
 */
public class AddressBookFragment extends MyFragment<HomeActivity> {

    public static AddressBookFragment newInstance() {
        return new AddressBookFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_address_book;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
