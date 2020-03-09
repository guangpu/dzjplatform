package com.hjq.base.action;

import com.hjq.base.R;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/09/21
 *    desc   : 动画样式
 */
public final class AnimStyle {

    /** 没有动画效果 */
    public static final int NO_ANIM = 0;

    /** 默认动画效果 */
    public static final int DEFAULT = R.style.ScaleAnimStyle;

    /** 缩放动画 */
    public static final int SCALE = R.style.ScaleAnimStyle;

    /** IOS 动画 */
    public static final int IOS = R.style.IOSAnimStyle;

    /** 吐司动画 */
    public static final int TOAST = android.R.style.Animation_Toast;

    /** 顶部弹出动画 */
    public static final int TOP = R.style.TopAnimStyle;

    /** 底部弹出动画 */
    public static final int BOTTOM = R.style.BottomAnimStyle;

    /** 左边弹出动画 */
    public static final int LEFT = R.style.LeftAnimStyle;

    /** 右边弹出动画 */
    public static final int RIGHT = R.style.RightAnimStyle;
}