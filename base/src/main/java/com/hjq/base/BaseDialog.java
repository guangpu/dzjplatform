package com.hjq.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.content.ContextCompat;

import com.hjq.base.action.AnimStyle;
import com.hjq.base.action.ClickAction;
import com.hjq.base.action.ContextAction;
import com.hjq.base.action.HandlerAction;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/11/24
 *    desc   : Dialog 基类
 */
public class BaseDialog extends AppCompatDialog implements ContextAction, HandlerAction, ClickAction,
        DialogInterface.OnShowListener, DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {

    /** Dialog 是否可以取消 */
    private boolean mCancelable = true;

    private final ListenersWrapper<BaseDialog> mListeners = new ListenersWrapper<>(this);

    private List<BaseDialog.OnShowListener> mShowListeners;
    private List<BaseDialog.OnCancelListener> mCancelListeners;
    private List<BaseDialog.OnDismissListener> mDismissListeners;

    public BaseDialog(Context context) {
        this(context, R.style.BaseDialogStyle);
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(mCancelable = flag);
    }

    /**
     * 获取 Dialog 的根布局
     */
    public View getContentView() {
        return findViewById(Window.ID_ANDROID_CONTENT);
    }

    /**
     * 是否设置了取消
     */
    public boolean isCancelable() {
        return mCancelable;
    }

    /**
     * 获取当前设置重心
     */
    public int getGravity() {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            return params.gravity;
        }

        return Gravity.NO_GRAVITY;
    }

    /**
     * 设置宽度
     */
    public void setWidth(int width) {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = width;
            window.setAttributes(params);
        }
    }

    /**
     * 设置高度
     */
    public void setHeight(int height) {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.height = height;
            window.setAttributes(params);
        }
    }

    /**
     * 设置 Dialog 重心
     */
    public void setGravity(int gravity) {
        Window window = getWindow();
        if (window != null) {
            window.setGravity(gravity);
        }
    }

    /**
     * 设置 Dialog 的动画
     */
    public void setWindowAnimations(@StyleRes int id) {
        Window window = getWindow();
        if (window != null) {
            window.setWindowAnimations(id);
        }
    }

    /**
     * 设置背景遮盖层开关
     */
    public void setBackgroundDimEnabled(boolean enabled) {
        Window window = getWindow();
        if (window != null) {
            if (enabled) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            } else {
                window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            }
        }
    }

    /**
     * 设置背景遮盖层的透明度（前提条件是背景遮盖层开关必须是为开启状态）
     */
    public void setBackgroundDimAmount(float dimAmount) {
        Window window = getWindow();
        if (window != null) {
            window.setDimAmount(dimAmount);
        }
    }

    @Override
    public void hide() {
        // 如果当前有 View 获得焦点，就必须将这个对话框 dismiss 掉，否则 Dialog 无法正常显示
        // 复现步骤：创建一个带有 EditText 的对话框，并弹出输入法，点击返回到桌面，然后再回来程序界面
        View view = getCurrentFocus();
        if (view != null) {
            dismiss();
        } else {
            super.hide();
        }
    }

    /**
     * 设置一个显示监听器
     *
     * @param listener       显示监听器对象
     * @deprecated           请使用 {@link #addOnShowListener(BaseDialog.OnShowListener)}}
     */
    @Deprecated
    @Override
    public void setOnShowListener(@Nullable DialogInterface.OnShowListener listener) {
        if (listener == null) {
            return;
        }
        addOnShowListener(new ShowListenerWrapper(listener));
    }

    /**
     * 设置一个取消监听器
     *
     * @param listener       取消监听器对象
     * @deprecated           请使用 {@link #addOnCancelListener(BaseDialog.OnCancelListener)}
     */
    @Deprecated
    @Override
    public void setOnCancelListener(@Nullable DialogInterface.OnCancelListener listener) {
        if (listener == null) {
            return;
        }
        addOnCancelListener(new CancelListenerWrapper(listener));
    }

    /**
     * 设置一个销毁监听器
     *
     * @param listener       销毁监听器对象
     * @deprecated           请使用 {@link #addOnDismissListener(BaseDialog.OnDismissListener)}
     */
    @Deprecated
    @Override
    public void setOnDismissListener(@Nullable DialogInterface.OnDismissListener listener) {
        if (listener == null) {
            return;
        }
        addOnDismissListener(new DismissListenerWrapper(listener));
    }

    /**
     * 设置一个按键监听器
     *
     * @param listener       按键监听器对象
     * @deprecated           请使用 {@link #setOnKeyListener(BaseDialog.OnKeyListener)}
     */
    @Deprecated
    @Override
    public void setOnKeyListener(@Nullable DialogInterface.OnKeyListener listener) {
        super.setOnKeyListener(listener);
    }

    public void setOnKeyListener(@Nullable BaseDialog.OnKeyListener listener) {
        super.setOnKeyListener(new KeyListenerWrapper(listener));
    }

    /**
     * 添加一个显示监听器
     *
     * @param listener      监听器对象
     */
    public void addOnShowListener(@Nullable BaseDialog.OnShowListener listener) {
        if (mShowListeners == null) {
            mShowListeners = new ArrayList<>();
            super.setOnShowListener(mListeners);
        }
        mShowListeners.add(listener);
    }

    /**
     * 添加一个取消监听器
     *
     * @param listener      监听器对象
     */
    public void addOnCancelListener(@Nullable BaseDialog.OnCancelListener listener) {
        if (mCancelListeners == null) {
            mCancelListeners = new ArrayList<>();
            super.setOnCancelListener(mListeners);
        }
        mCancelListeners.add(listener);
    }

    /**
     * 添加一个销毁监听器
     *
     * @param listener      监听器对象
     */
    public void addOnDismissListener(@Nullable BaseDialog.OnDismissListener listener) {
        if (mDismissListeners == null) {
            mDismissListeners = new ArrayList<>();
            super.setOnDismissListener(mListeners);
        }
        mDismissListeners.add(listener);
    }

    /**
     * 移除一个显示监听器
     *
     * @param listener      监听器对象
     */
    public void removeOnShowListener(@Nullable BaseDialog.OnShowListener listener) {
        if (mShowListeners != null) {
            mShowListeners.remove(listener);
        }
    }

    /**
     * 移除一个取消监听器
     *
     * @param listener      监听器对象
     */
    public void removeOnCancelListener(@Nullable BaseDialog.OnCancelListener listener) {
        if (mCancelListeners != null) {
            mCancelListeners.remove(listener);
        }
    }

    /**
     * 移除一个销毁监听器
     *
     * @param listener      监听器对象
     */
    public void removeOnDismissListener(@Nullable BaseDialog.OnDismissListener listener) {
        if (mDismissListeners != null) {
            mDismissListeners.remove(listener);
        }
    }

    /**
     * 设置显示监听器集合
     */
    private void setOnShowListeners(@Nullable List<BaseDialog.OnShowListener> listeners) {
        super.setOnShowListener(mListeners);
        mShowListeners = listeners;
    }

    /**
     * 设置取消监听器集合
     */
    private void setOnCancelListeners(@Nullable List<BaseDialog.OnCancelListener> listeners) {
        super.setOnCancelListener(mListeners);
        mCancelListeners = listeners;
    }

    /**
     * 设置销毁监听器集合
     */
    private void setOnDismissListeners(@Nullable List<BaseDialog.OnDismissListener> listeners) {
        super.setOnDismissListener(mListeners);
        mDismissListeners = listeners;
    }

    /**
     * {@link DialogInterface.OnShowListener}
     */
    @Override
    public void onShow(DialogInterface dialog) {
        if (mShowListeners != null) {
            for (BaseDialog.OnShowListener listener : mShowListeners) {
                listener.onShow(this);
            }
        }
    }

    /**
     * {@link DialogInterface.OnCancelListener}
     */
    @Override
    public void onCancel(DialogInterface dialog) {
        if (mCancelListeners != null) {
            for (BaseDialog.OnCancelListener listener : mCancelListeners) {
                listener.onCancel(this);
            }
        }
    }

    /**
     * {@link DialogInterface.OnDismissListener}
     */
    @Override
    public void onDismiss(DialogInterface dialog) {
        if (mDismissListeners != null) {
            for (BaseDialog.OnDismissListener listener : mDismissListeners) {
                listener.onDismiss(this);
            }
        }
    }

    @Override
    protected void onStop() {
        removeCallbacks();
        super.onStop();
    }

    @SuppressWarnings("unchecked")
    public static class Builder<B extends Builder> implements ContextAction, ClickAction {

        /** Context 对象 */
        private final Context mContext;
        /** Dialog 对象 */
        private BaseDialog mDialog;
        /** Dialog 布局 */
        private View mContentView;

        /** 主题 */
        private int mThemeId = R.style.BaseDialogStyle;
        /** 动画 */
        private int mAnimations = AnimStyle.NO_ANIM;
        /** 位置 */
        private int mGravity = Gravity.NO_GRAVITY;
        /** 宽度和高度 */
        private int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        private int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
        /** 背景遮盖层开关 */
        private boolean mBackgroundDimEnabled = true;
        /** 背景遮盖层透明度 */
        private float mBackgroundDimAmount = 0.5f;
        /** 是否能够被取消 */
        private boolean mCancelable = true;
        /** 点击空白是否能够取消  前提是这个对话框可以被取消 */
        private boolean mCanceledOnTouchOutside = true;

        /** Dialog Show 监听 */
        private List<BaseDialog.OnShowListener> mOnShowListeners;
        /** Dialog Cancel 监听 */
        private List<BaseDialog.OnCancelListener> mOnCancelListeners;
        /** Dialog Dismiss 监听 */
        private List<BaseDialog.OnDismissListener> mOnDismissListeners;
        /** Dialog Key 监听 */
        private BaseDialog.OnKeyListener mOnKeyListener;

        /** 点击事件集合 */
        private SparseArray<BaseDialog.OnClickListener> mClickArray;

        public Builder(Context context) {
            mContext = context;
        }

        /**
         * 设置主题 id
         */
        public B setThemeStyle(@StyleRes int id) {
            if (isCreated()) {
                // Dialog 创建之后不能再设置主题 id
                throw new IllegalStateException("are you ok?");
            }
            mThemeId = id;
            return (B) this;
        }

        /**
         * 设置布局
         */
        public B setContentView(@LayoutRes int id) {
            // 这里解释一下，为什么要传 new FrameLayout，因为如果不传的话，XML 的根布局获取到的 LayoutParams 对象会为空，也就会导致宽高解析不出来
            return setContentView(LayoutInflater.from(mContext).inflate(id, new FrameLayout(mContext), false));
        }
        public B setContentView(View view) {
            mContentView = view;

            if (isCreated()) {
                mDialog.setContentView(view);
            } else {
                if (mContentView != null) {
                    ViewGroup.LayoutParams layoutParams = mContentView.getLayoutParams();
                    if (layoutParams != null && mWidth == ViewGroup.LayoutParams.WRAP_CONTENT && mHeight == ViewGroup.LayoutParams.WRAP_CONTENT) {
                        // 如果当前 Dialog 的宽高设置了自适应，就以布局中设置的宽高为主
                        setWidth(layoutParams.width);
                        setHeight(layoutParams.height);
                    }

                    // 如果当前没有设置重心，就自动获取布局重心
                    if (mGravity == Gravity.NO_GRAVITY) {
                        if (layoutParams instanceof FrameLayout.LayoutParams) {
                            setGravity(((FrameLayout.LayoutParams) layoutParams).gravity);
                        } else if (layoutParams instanceof LinearLayout.LayoutParams) {
                            setGravity(((LinearLayout.LayoutParams) layoutParams).gravity);
                        } else {
                            // 默认重心是居中
                            setGravity(Gravity.CENTER);
                        }
                    }
                }
            }
            return (B) this;
        }

        /**
         * 设置重心位置
         */
        public B setGravity(int gravity) {
            // 适配 Android 4.2 新特性，布局反方向（开发者选项 - 强制使用从右到左的布局方向）
            mGravity = Gravity.getAbsoluteGravity(gravity, getResources().getConfiguration().getLayoutDirection());
            if (isCreated()) {
                mDialog.setGravity(gravity);
            }
            return (B) this;
        }

        /**
         * 设置宽度
         */
        public B setWidth(int width) {
            mWidth = width;
            if (isCreated()) {
                mDialog.setWidth(width);
            } else {
                ViewGroup.LayoutParams params = mContentView != null ? mContentView.getLayoutParams() : null;
                if (params != null) {
                    params.width = width;
                    mContentView.setLayoutParams(params);
                }
            }
            return (B) this;
        }

        /**
         * 设置高度
         */
        public B setHeight(int height) {
            mHeight = height;
            if (isCreated()) {
                mDialog.setHeight(height);
            } else {
                // 这里解释一下为什么要重新设置 LayoutParams
                // 因为如果不这样设置的话，第一次显示的时候会按照 Dialog 宽高显示
                // 但是 Layout 内容变更之后就不会按照之前的设置宽高来显示
                // 所以这里我们需要对 View 的 LayoutParams 也进行设置
                ViewGroup.LayoutParams params = mContentView != null ? mContentView.getLayoutParams() : null;
                if (params != null) {
                    params.height = height;
                    mContentView.setLayoutParams(params);
                }
            }
            return (B) this;
        }

        /**
         * 是否可以取消
         */
        public B setCancelable(boolean cancelable) {
            mCancelable = cancelable;
            if (isCreated()) {
                mDialog.setCancelable(cancelable);
            }
            return (B) this;
        }

        /**
         * 是否可以通过点击空白区域取消
         */
        public B setCanceledOnTouchOutside(boolean cancel) {
            mCanceledOnTouchOutside = cancel;
            if (isCreated() && mCancelable) {
                mDialog.setCanceledOnTouchOutside(cancel);
            }
            return (B) this;
        }

        /**
         * 设置动画，已经封装好几种样式，具体可见{@link AnimStyle}类
         */
        public B setAnimStyle(@StyleRes int id) {
            mAnimations = id;
            if (isCreated()) {
                mDialog.setWindowAnimations(id);
            }
            return (B) this;
        }

        /**
         * 设置背景遮盖层开关
         */
        public void setBackgroundDimEnabled(boolean enabled) {
            mBackgroundDimEnabled = enabled;
            if (isCreated()) {
                mDialog.setBackgroundDimEnabled(enabled);
            }
        }

        /**
         * 设置背景遮盖层的透明度（前提条件是背景遮盖层开关必须是为开启状态）
         */
        public void setBackgroundDimAmount(float dimAmount) {
            mBackgroundDimAmount = dimAmount;
            if (isCreated()) {
                mDialog.setBackgroundDimAmount(dimAmount);
            }
        }

        /**
         * 添加显示监听
         */
        public B addOnShowListener(@NonNull BaseDialog.OnShowListener listener) {
            if (isCreated()) {
                mDialog.addOnShowListener(listener);
            } else {
                if (mOnShowListeners == null) {
                    mOnShowListeners = new ArrayList<>();
                }
                mOnShowListeners.add(listener);
            }
            return (B) this;
        }

        /**
         * 添加取消监听
         */
        public B addOnCancelListener(@NonNull BaseDialog.OnCancelListener listener) {
            if (isCreated()) {
                mDialog.addOnCancelListener(listener);
            } else {
                if (mOnCancelListeners == null) {
                    mOnCancelListeners = new ArrayList<>();
                }
                mOnCancelListeners.add(listener);
            }
            return (B) this;
        }

        /**
         * 添加销毁监听
         */
        public B addOnDismissListener(@NonNull BaseDialog.OnDismissListener listener) {
            if (isCreated()) {
                mDialog.addOnDismissListener(listener);
            } else {
                if (mOnDismissListeners == null) {
                    mOnDismissListeners = new ArrayList<>();
                }
                mOnDismissListeners.add(listener);
            }
            return (B) this;
        }

        /**
         * 设置按键监听
         */
        public B setOnKeyListener(@NonNull BaseDialog.OnKeyListener listener) {
            if (isCreated()) {
                mDialog.setOnKeyListener(listener);
            } else {
                mOnKeyListener = listener;
            }
            return (B) this;
        }

        /**
         * 设置文本
         */
        public B setText(@IdRes int viewId, @StringRes int stringId) {
            return setText(viewId, getString(stringId));
        }
        public B setText(@IdRes int id, CharSequence text) {
            ((TextView) findViewById(id)).setText(text);
            return (B) this;
        }

        /**
         * 设置可见状态
         */
        public B setVisibility(@IdRes int id, int visibility) {
            findViewById(id).setVisibility(visibility);
            return (B) this;
        }

        /**
         * 设置背景
         */
        public B setBackground(@IdRes int viewId, @DrawableRes int drawableId) {
            return setBackground(viewId, ContextCompat.getDrawable(mContext, drawableId));
        }
        public B setBackground(@IdRes int id, Drawable drawable) {
            findViewById(id).setBackground(drawable);
            return (B) this;
        }

        /**
         * 设置图片
         */
        public B setImageDrawable(@IdRes int viewId, @DrawableRes int drawableId) {
            return setBackground(viewId, ContextCompat.getDrawable(mContext, drawableId));
        }
        public B setImageDrawable(@IdRes int id, Drawable drawable) {
            ((ImageView) findViewById(id)).setImageDrawable(drawable);
            return (B) this;
        }

        /**
         * 设置点击事件
         */
        public B setOnClickListener(@IdRes int id, @NonNull BaseDialog.OnClickListener listener) {
            if (isCreated()) {
                View view = mDialog.findViewById(id);
                if (view != null) {
                    view.setOnClickListener(new ViewClickWrapper(mDialog, listener));
                }
            } else {
                if (mClickArray == null) {
                    mClickArray = new SparseArray<>();
                }
                mClickArray.put(id, listener);
            }
            return (B) this;
        }

        /**
         * 创建
         */
        @SuppressLint("RtlHardcoded")
        public BaseDialog create() {

            // 判断布局是否为空
            if (mContentView == null) {
                throw new IllegalArgumentException("are you ok?");
            }

            // 如果当前没有设置重心，就设置一个默认的重心
            if (mGravity == Gravity.NO_GRAVITY) {
                mGravity = Gravity.CENTER;
            }

            // 如果当前没有设置动画效果，就设置一个默认的动画效果
            if (mAnimations == AnimStyle.NO_ANIM) {
                switch (mGravity) {
                    case Gravity.TOP:
                        mAnimations = AnimStyle.TOP;
                        break;
                    case Gravity.BOTTOM:
                        mAnimations = AnimStyle.BOTTOM;
                        break;
                    case Gravity.LEFT:
                        mAnimations = AnimStyle.LEFT;
                        break;
                    case Gravity.RIGHT:
                        mAnimations = AnimStyle.RIGHT;
                        break;
                    default:
                        mAnimations = AnimStyle.DEFAULT;
                        break;
                }
            }

            mDialog = createDialog(mContext, mThemeId);

            mDialog.setContentView(mContentView);
            mDialog.setCancelable(mCancelable);
            if (mCancelable) {
                mDialog.setCanceledOnTouchOutside(mCanceledOnTouchOutside);
            }

            // 设置参数
            Window window = mDialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams params = window.getAttributes();
                params.width = mWidth;
                params.height = mHeight;
                params.gravity = mGravity;
                params.windowAnimations = mAnimations;
                window.setAttributes(params);
                if (mBackgroundDimEnabled) {
                    window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    window.setDimAmount(mBackgroundDimAmount);
                } else {
                    window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                }
            }

            if (mOnShowListeners != null) {
                mDialog.setOnShowListeners(mOnShowListeners);
            }

            if (mOnCancelListeners != null) {
                mDialog.setOnCancelListeners(mOnCancelListeners);
            }

            if (mOnDismissListeners != null) {
                mDialog.setOnDismissListeners(mOnDismissListeners);
            }

            if (mOnKeyListener != null) {
                mDialog.setOnKeyListener(mOnKeyListener);
            }

            for (int i = 0; mClickArray != null && i < mClickArray.size(); i++) {
                mContentView.findViewById(mClickArray.keyAt(i)).setOnClickListener(new ViewClickWrapper(mDialog, mClickArray.valueAt(i)));
            }

            return mDialog;
        }

        /**
         * 显示
         */
        public BaseDialog show() {
            final BaseDialog dialog = create();
            dialog.show();
            return dialog;
        }

        @Override
        public Context getContext() {
            return mContext;
        }

        /**
         * 当前 Dialog 是否创建了
         */
        public boolean isCreated() {
            return mDialog != null;
        }

        /**
         * 当前 Dialog 是否显示了
         */
        public boolean isShowing() {
            return isCreated() && mDialog.isShowing();
        }
        /**
         * 销毁当前 Dialog
         */
        public void dismiss() {
            if (mDialog != null) {
                mDialog.dismiss();
            }
        }

        /**
         * 创建 Dialog 对象（子类可以重写此方法来改变 Dialog 类型）
         */
        protected BaseDialog createDialog(Context context, @StyleRes int themeId) {
            return new BaseDialog(context, themeId);
        }

        /**
         * 延迟执行
         */
        public final void post(Runnable r) {
            if (isShowing()) {
                mDialog.post(r);
            } else {
                addOnShowListener(new ShowPostWrapper(r));
            }
        }

        /**
         * 延迟一段时间执行
         */
        public final void postDelayed(Runnable r, long delayMillis) {
            if (isShowing()) {
                mDialog.postDelayed(r, delayMillis);
            } else {
                addOnShowListener(new ShowPostDelayedWrapper(r, delayMillis));
            }
        }

        /**
         * 在指定的时间执行
         */
        public final void postAtTime(Runnable r, long uptimeMillis) {
            if (isShowing()) {
                mDialog.postAtTime(r, uptimeMillis);
            } else {
                addOnShowListener(new ShowPostAtTimeWrapper(r, uptimeMillis));
            }
        }

        /**
         * 获取 Dialog 的根布局
         */
        public View getContentView() {
            return mContentView;
        }

        /**
         * 根据 id 查找 View
         */
        @Override
        public  <V extends View> V findViewById(@IdRes int id) {
            if (mContentView == null) {
                // 没有 setContentView 就想 findViewById ?
                throw new IllegalStateException("are you ok?");
            }
            return mContentView.findViewById(id);
        }

        /**
         * 获取当前 Dialog 对象
         */
        @Nullable
        public BaseDialog getDialog() {
            return mDialog;
        }
    }

    /**
     * Dialog 监听包装类（修复原生 Dialog 监听器对象导致的内存泄漏）
     */
    private static final class ListenersWrapper<T extends DialogInterface.OnShowListener & DialogInterface.OnCancelListener & DialogInterface.OnDismissListener>
                        extends WeakReference<T> implements DialogInterface.OnShowListener, DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {

        private ListenersWrapper(T referent) {
            super(referent);
        }

        @Override
        public void onShow(DialogInterface dialog) {
            if (get() != null) {
                get().onShow(dialog);
            }
        }

        @Override
        public void onCancel(DialogInterface dialog) {
            if (get() != null) {
                get().onCancel(dialog);
            }
        }

        @Override
        public void onDismiss(DialogInterface dialog) {
            if (get() != null) {
                get().onDismiss(dialog);
            }
        }
    }

    /**
     * 点击事件包装类
     */
    private static final class ViewClickWrapper
            implements View.OnClickListener {

        private final BaseDialog mDialog;
        private final BaseDialog.OnClickListener mListener;

        private ViewClickWrapper(BaseDialog dialog, BaseDialog.OnClickListener listener) {
            mDialog = dialog;
            mListener = listener;
        }

        @SuppressWarnings("unchecked")
        @Override
        public final void onClick(View v) {
            mListener.onClick(mDialog, v);
        }
    }

    /**
     * 显示监听包装类
     */
    private static final class ShowListenerWrapper
            extends WeakReference<DialogInterface.OnShowListener>
            implements BaseDialog.OnShowListener {

        private ShowListenerWrapper(DialogInterface.OnShowListener referent) {
            super(referent);
        }

        @Override
        public void onShow(BaseDialog dialog) {
            // 在横竖屏切换后监听对象会为空
            if (get() != null) {
                get().onShow(dialog);
            }
        }
    }

    /**
     * 取消监听包装类
     */
    private static final class CancelListenerWrapper
            extends WeakReference<DialogInterface.OnCancelListener>
            implements BaseDialog.OnCancelListener {

        private CancelListenerWrapper(DialogInterface.OnCancelListener referent) {
            super(referent);
        }

        @Override
        public void onCancel(BaseDialog dialog) {
            // 在横竖屏切换后监听对象会为空
            if (get() != null) {
                get().onCancel(dialog);
            }
        }
    }

    /**
     * 销毁监听包装类
     */
    private static final class DismissListenerWrapper
            extends WeakReference<DialogInterface.OnDismissListener>
            implements BaseDialog.OnDismissListener {

        private DismissListenerWrapper(DialogInterface.OnDismissListener referent) {
            super(referent);
        }

        @Override
        public void onDismiss(BaseDialog dialog) {
            // 在横竖屏切换后监听对象会为空
            if (get() != null) {
                get().onDismiss(dialog);
            }
        }
    }

    /**
     * 按键监听包装类
     */
    private static final class KeyListenerWrapper
            implements DialogInterface.OnKeyListener {

        private final BaseDialog.OnKeyListener mListener;

        private KeyListenerWrapper(BaseDialog.OnKeyListener listener) {
            mListener = listener;
        }

        @Override
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            // 在横竖屏切换后监听对象会为空
            if (mListener != null && dialog instanceof BaseDialog) {
                mListener.onKey((BaseDialog) dialog, event);
            }
            return false;
        }
    }

    /**
     * post 任务包装类
     */
    private static final class ShowPostWrapper implements OnShowListener {

        private final Runnable mRunnable;

        private ShowPostWrapper(Runnable r) {
            mRunnable = r;
        }

        @Override
        public void onShow(BaseDialog dialog) {
            if (mRunnable != null) {
                dialog.removeOnShowListener(this);
                dialog.post(mRunnable);
            }
        }
    }

    /**
     * postDelayed 任务包装类
     */
    private static final class ShowPostDelayedWrapper implements OnShowListener {

        private final Runnable mRunnable;
        private final long mDelayMillis;

        private ShowPostDelayedWrapper(Runnable r, long delayMillis) {
            mRunnable = r;
            mDelayMillis = delayMillis;
        }

        @Override
        public void onShow(BaseDialog dialog) {
            if (mRunnable != null) {
                dialog.removeOnShowListener(this);
                dialog.postDelayed(mRunnable, mDelayMillis);
            }
        }
    }

    /**
     * postAtTime 任务包装类
     */
    private static final class ShowPostAtTimeWrapper implements OnShowListener {

        private final Runnable mRunnable;
        private final long mUptimeMillis;

        private ShowPostAtTimeWrapper(Runnable r, long uptimeMillis) {
            mRunnable = r;
            mUptimeMillis = uptimeMillis;
        }

        @Override
        public void onShow(BaseDialog dialog) {
            if (mRunnable != null) {
                dialog.removeOnShowListener(this);
                dialog.postAtTime(mRunnable, mUptimeMillis);
            }
        }
    }

    /**
     * 点击监听器
     */
    public interface OnClickListener<V extends View> {
        void onClick(BaseDialog dialog, V view);
    }

    /**
     * 显示监听器
     */
    public interface OnShowListener {

        /**
         * Dialog 显示了
         */
        void onShow(BaseDialog dialog);
    }

    /**
     * 取消监听器
     */
    public interface OnCancelListener {

        /**
         * Dialog 取消了
         */
        void onCancel(BaseDialog dialog);
    }

    /**
     * 销毁监听器
     */
    public interface OnDismissListener {

        /**
         * Dialog 销毁了
         */
        void onDismiss(BaseDialog dialog);
    }

    /**
     * 按键监听器
     */
    public interface OnKeyListener {

        /**
         * 触发了按键
         */
        boolean onKey(BaseDialog dialog, KeyEvent event);
    }

    /**
     * Dialog 动画样式
     */
    public static final class AnimStyle {

        /** 没有动画效果 */
        public static final int NO_ANIM = 0;

        /** 默认动画效果 */
        static final int DEFAULT = R.style.ScaleAnimStyle;

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
}