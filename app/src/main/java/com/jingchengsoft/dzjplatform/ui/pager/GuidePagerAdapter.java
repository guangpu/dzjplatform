package com.jingchengsoft.dzjplatform.ui.pager;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.PagerAdapter;

import com.jingchengsoft.dzjplatform.R;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/09/21
 *    desc   : 引导页适配器
 */
public final class GuidePagerAdapter extends PagerAdapter {

    private static final int[] DRAWABLES = {R.drawable.bg_guide_1, R.drawable.bg_guide_2, R.drawable.bg_guide_3};

    @Override
    public int getCount() {
        return DRAWABLES.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        AppCompatImageView view = new AppCompatImageView(container.getContext());
        view.setImageResource(DRAWABLES[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}