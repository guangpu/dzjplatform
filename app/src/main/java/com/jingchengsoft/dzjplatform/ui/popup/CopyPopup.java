package com.jingchengsoft.dzjplatform.ui.popup;

import androidx.fragment.app.FragmentActivity;

import com.hjq.base.BasePopupWindow;
import com.jingchengsoft.dzjplatform.R;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/10/18
 *    desc   : 可进行拷贝的副本
 */
public final class CopyPopup {

    public static final class Builder
            extends BasePopupWindow.Builder<Builder> {

        public Builder(FragmentActivity activity) {
            super(activity);

            setContentView(R.layout.popup_copy);
        }
    }
}