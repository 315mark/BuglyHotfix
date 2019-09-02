package com.zkch.bugly.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zkch.bugly.R;
import com.zkch.bugly.bean.Joker;

public class JokerAdapter extends BaseQuickAdapter<Joker.DataBean, BaseViewHolder> {

    public JokerAdapter() {
        super(R.layout.item_joker);
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, Joker.DataBean item) {
        helper.setText(R.id.title,item.getName())
        .setText(R.id.content,item.getText())
        .addOnClickListener(R.id.img);
        Glide.with(mContext).load(item.getBimageuri()).into((ImageView) helper.getView(R.id.ivNews));
    }
}
