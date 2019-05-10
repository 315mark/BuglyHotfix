package com.zkch.bugly.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zkch.bugly.R;
import com.zkch.bugly.bean.ImageBean;
import com.zkch.bugly.widget.TextViewDrawableTarget;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class ImageLoaderUtils {

    public static void display(Context context, ImageView imageView, String url, int placeholder, int error) {
        if(imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).apply(new RequestOptions()
                .placeholder(placeholder)
                .error(error))
                //使用变换效果
                .transition(withCrossFade())
                .into(imageView);
    }

    public static void display(Context context, ImageView imageView, String url) {
        if(imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).apply(new RequestOptions()
                //加载占位符
                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_image_loadfail))

                .transition(withCrossFade())
                .into(imageView);
    }

    public static void disTextView(Context context, TextView tv, String url) {
        if(tv == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).apply(new RequestOptions()
                //加载占位符
                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_image_loadfail)
                .override(400,400) )

                .transition(withCrossFade())
                .into(new TextViewDrawableTarget(tv));
    }

    // .override(400,400)指定图片的尺寸
    // 指定图片的缩放类型为fitCenter
    // .fitCenter() （等比例缩放图片，宽或者是高等于ImageView的宽或者是高。）
    // （等比例缩放图片，直到图片的宽高都大于等于ImageView的宽度，然后截取中间的显示）
    // .centerCrop() 指定图片的缩放类型为centerCrop
    // .circleCrop()指定图片的缩放类型为centerCrop （圆形）

    // .skipMemoryCache(true)跳过内存缓存
    // .diskCacheStrategy(DiskCacheStrategy.ALL)缓存所有版本的图像
    // .diskCacheStrategy(DiskCacheStrategy.NONE)跳过磁盘缓存
    // .diskCacheStrategy(DiskCacheStrategy.DATA)只缓存原来分辨率的图片
    // .diskCacheStrategy(DiskCacheStrategy.RESOURCE)只缓存最终的图片 ;

}
