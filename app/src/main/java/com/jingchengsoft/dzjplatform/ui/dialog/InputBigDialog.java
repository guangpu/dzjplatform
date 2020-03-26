package com.jingchengsoft.dzjplatform.ui.dialog;

import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentActivity;

import com.hjq.base.BaseDialog;
import com.jingchengsoft.dzjplatform.R;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/02/27
 *    desc   : 输入对话框
 */
public final class InputBigDialog {

    public static final class Builder
            extends UIDialog.Builder<InputBigDialog.Builder>
            implements View.OnClickListener,
            BaseDialog.OnShowListener,
            BaseDialog.OnDismissListener {

        private OnListener mListener;
        private boolean mAutoDismiss = true;

        private final TextView mTitleView;
        private final EditText mInputView;

        private final TextView mCancelView;
        private final View mLineView;
        private final TextView mConfirmView;

        public Builder(FragmentActivity activity) {
            super(activity);
            setContentView(R.layout.dialog_big_input);
            setAnimStyle(BaseDialog.AnimStyle.IOS);

            mTitleView = findViewById(R.id.tv_big_input_title);
            mInputView = findViewById(R.id.tv_big_input_message);

            mCancelView  = findViewById(R.id.tv_big_input_cancel);
            mLineView = findViewById(R.id.v_big_input_line);
            mConfirmView  = findViewById(R.id.tv_big_input_confirm);

            mCancelView.setOnClickListener(this);
            mConfirmView.setOnClickListener(this);

            addOnShowListener(this);
            addOnDismissListener(this);
        }

        public Builder setHint(@StringRes int id) {
            return setHint(getString(id));
        }
        public Builder setHint(CharSequence text) {
            mInputView.setHint(text);
            return this;
        }

        public Builder setTitle(@StringRes int id) {
            return setTitle(getString(id));
        }

        public Builder setTitle(CharSequence text) {
            mTitleView.setText(text);
            return this;
        }

        public Builder setContent(@StringRes int id) {
            return setContent(getString(id));
        }
        public Builder setContent(CharSequence text) {
            mInputView.setText(text);
            int index = mInputView.getText().toString().length();
            if (index > 0) {
                mInputView.requestFocus();
                mInputView.setSelection(index);
            }
            return this;
        }

        public Builder setListener(OnListener listener) {
            mListener = listener;
            return this;
        }

        /**
         * {@link BaseDialog.OnShowListener}
         */
        @Override
        public void onShow(BaseDialog dialog) {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    getSystemService(InputMethodManager.class).showSoftInput(mInputView, 0);
                }
            }, 500);
        }

        /**
         * {@link BaseDialog.OnDismissListener}
         */
        @Override
        public void onDismiss(BaseDialog dialog) {
            getSystemService(InputMethodManager.class).hideSoftInputFromWindow(mInputView.getWindowToken(), 0);
        }

        /**
         * {@link View.OnClickListener}
         */
        @Override
        public void onClick(View v) {
            if (mAutoDismiss) {
                dismiss();
            }

            if (mListener != null) {
                if (v == mConfirmView) {
                    // 判断输入是否为空
                    mListener.onConfirm(getDialog(), mInputView.getText().toString());
                } else if (v == mCancelView) {
                    mListener.onCancel(getDialog());
                }
            }
        }
    }

    public interface OnListener {

        /**
         * 点击确定时回调
         */
        void onConfirm(BaseDialog dialog, String content);

        /**
         * 点击取消时回调
         */
        void onCancel(BaseDialog dialog);
    }
}