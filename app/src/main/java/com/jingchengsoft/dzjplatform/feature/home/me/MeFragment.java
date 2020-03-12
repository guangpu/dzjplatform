package com.jingchengsoft.dzjplatform.feature.home.me;

import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.hjq.widget.layout.SettingBar;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.common.MyFragment;
import com.jingchengsoft.dzjplatform.feature.home.HomeActivity;
import com.jingchengsoft.dzjplatform.feature.login.LoginActivity;
import com.jingchengsoft.dzjplatform.sp.SpUser;

import butterknife.BindView;

/**
 * author : wgp
 * time   :  2020/3/10
 * desc   :
 */
public class MeFragment extends MyFragment<HomeActivity> {
    public static MeFragment newInstance() {
        return new MeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @BindView(R.id.sb_person_data_name)
    SettingBar sbPersonName;

    @Override
    protected void initView() {
        findViewById(R.id.bt_me_exit).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        LogUtils.i(" SpUser.username:" + SpUser.INSTANCE.getUsername());
        sbPersonName.setRightText(SpUser.INSTANCE.getUsername());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_me_exit:
                SpUser.INSTANCE.setToken("");
                startActivity(LoginActivity.class);
                finish();
                break;
        }
    }

    @Override
    protected boolean statusBarDarkFont() {
        return false;
    }
}
