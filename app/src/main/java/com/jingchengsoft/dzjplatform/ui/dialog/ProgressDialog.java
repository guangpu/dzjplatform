package com.jingchengsoft.dzjplatform.ui.dialog;

import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.hjq.base.BaseDialogFragment;
import com.hjq.base.action.AnimStyle;
import com.jingchengsoft.dzjplatform.R;
import com.jingchengsoft.dzjplatform.ui.widget.progress.NumberProgressBar;


public final class ProgressDialog {


    private static TextView mMessageView;
    private static NumberProgressBar progressBar;

    public ProgressDialog() {
    }

    public static final class Builder
            extends BaseDialogFragment.Builder<Builder> {

        public Builder(FragmentActivity activity) {
            super(activity);
            setContentView(R.layout.dialog_process);
            setAnimStyle(AnimStyle.IOS);
            setBackgroundDimEnabled(true);
            setCancelable(false);
            mMessageView = findViewById(R.id.stv_progress);
            progressBar = findViewById(R.id.npb_progress);
            progressBar.setReachedBarHeight(10);
            progressBar.setUnreachedBarHeight(10);
        }
        public ProgressDialog.Builder setMessage(CharSequence text) {
            mMessageView.setText(text);
            mMessageView.setVisibility(text == null ? View.GONE : View.VISIBLE);
            return this;
        }

    }

    public static void setProgress(int num){
        progressBar.setProgress(num);
    }
}