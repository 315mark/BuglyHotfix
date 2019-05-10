package com.zkch.bugly.widget;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.zkch.bugly.MyApp;
import com.zkch.bugly.R;

public class TextViewDrawableTarget extends CustomViewTarget<TextView, Drawable> {

    /**
     * Constructor that defaults {@code waitForLayout} to {@code false}.
     *
     * @param view
     */
    public TextViewDrawableTarget(@NonNull TextView view) {
        super(view);
    }

    @Override
    protected void onResourceCleared(@Nullable Drawable placeholder) {

    }


    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) {
        //加载失败例如url=null，此时使用 fallback不生效
        view.setCompoundDrawablesWithIntrinsicBounds(null, MyApp.getContext().getDrawable(R.drawable.ic_image_loadfail), null, null);

    }

    /**
     * The method that will be called when the resource load has finished.
     *
     * @param resource   the loaded resource.
     * @param transition
     */
    @Override
    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
        //意思是设置Drawable显示在text的左、上、右、下位置。
        view.setCompoundDrawablesWithIntrinsicBounds(null, null, null, resource);
        view.setCompoundDrawablePadding(10);
    }

}
