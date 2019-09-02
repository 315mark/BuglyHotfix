package com.zkch.bugly.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zkch.bugly.R;
import com.zkch.bugly.api.ApiService;
import com.zkch.bugly.bean.ImageBean;
import com.zkch.bugly.widget.TextViewDrawableTarget;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

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


    //多张下载保存本地
    public void downImg(){
        ArrayList<String> images = new ArrayList<>();
//        ApiService apiService = RetrofitService.getInstance().getApiService();
        //注意：此处是保存多张图片，可以采用异步线程
        ArrayList<Observable<Boolean>> observables = new ArrayList<>();
        AtomicInteger count = new AtomicInteger();
        for (String image : images){
//            observables.add(apiService.downloadImage(image)
//                    .subscribeOn(Schedulers.io())
//                    .map(new Function<ResponseBody, Boolean>() {
//                        @Override
//                        public Boolean apply(ResponseBody responseBody) throws Exception {
//                            saveIo(responseBody.byteStream());
//                            return true;
//                        }
//                    }));
        }


    // observable的merge 将所有的observable合成一个Observable，所有的observable同时发射数据
    Disposable subscribe = Observable.merge(observables).observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<Boolean>() {
                @Override
                public void accept(Boolean b) throws Exception {
                    if (b) {
                        count.addAndGet(1);
                        Log.e("yc", "download is succcess");

                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    Log.e("yc", "download error");
                }
            }, new Action() {
                @Override
                public void run() throws Exception {
                    Log.e("yc", "download complete");
                    // 下载成功的数量 和 图片集合的数量一致，说明全部下载成功了
                    if (images.size() == count.get()) {
//                        ToastUtils.showRoundRectToast("保存成功");
                    } else {
                        if (count.get() == 0) {
//                            ToastUtils.showRoundRectToast("保存失败");
                        } else {
//                            ToastUtils.showRoundRectToast("因网络问题 保存成功" + count + ",保存失败" + (images.size() - count.get()));
                        }
                    }
                }
            }, new Consumer<Disposable>() {
                @Override
                public void accept(Disposable disposable) throws Exception {
                    Log.e("yc","disposable");
                }
            });
    }
    private void saveIo(InputStream inputStream){
        String localImgSavePath /*= FileSaveUtils.getLocalImgSavePath()*/ = null;
        File imageFile = new File(localImgSavePath);
        if (!imageFile.exists()) {
            imageFile.getParentFile().mkdirs();
            try {
                imageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        try {
            fos = new FileOutputStream(imageFile);
            bis = new BufferedInputStream(inputStream);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //刷新相册代码省略……
        }
    }



}
