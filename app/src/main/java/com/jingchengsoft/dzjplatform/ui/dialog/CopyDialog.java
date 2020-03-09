package com.jingchengsoft.dzjplatform.ui.dialog;

import android.view.Gravity;

import androidx.fragment.app.FragmentActivity;

import com.hjq.base.BaseDialogFragment;
import com.hjq.base.action.AnimStyle;
import com.jingchengsoft.dzjplatform.R;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 可进行拷贝的副本
 */
public final class CopyDialog {

    public static final class Builder
            extends BaseDialogFragment.Builder<Builder> {

        public Builder(FragmentActivity activity) {
            super(activity);

            setContentView(R.layout.dialog_copy);
            setAnimStyle(AnimStyle.BOTTOM);
            setGravity(Gravity.BOTTOM);
        }
    }
}