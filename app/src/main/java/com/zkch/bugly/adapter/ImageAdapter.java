package com.zkch.bugly.adapter;

import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zkch.bugly.R;
import com.zkch.bugly.bean.ImageBean;
import com.zkch.bugly.utils.ImageLoaderUtils;

public class ImageAdapter extends BaseQuickAdapter<ImageBean, BaseViewHolder> {

    public ImageAdapter() {
        super(R.layout.item_image);

    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, ImageBean item) {
        TextView tvTxt = helper.getView(R.id.tvTitle);
        ImageLoaderUtils.disTextView(mContext,tvTxt,item.getThumburl());
        tvTxt.setText(item.getTitle());
       // helper.setText(R.id.tvTitle,item.getTitle());
    }
}
