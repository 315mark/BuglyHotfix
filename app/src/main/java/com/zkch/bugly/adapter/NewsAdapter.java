package com.zkch.bugly.adapter;

import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zkch.bugly.R;
import com.zkch.bugly.bean.NewsBean;

public class NewsAdapter extends BaseQuickAdapter <NewsBean,BaseViewHolder>{

    public NewsAdapter() {
        super(R.layout.item_news);
    }

    @Override
    public int addFooterView(View footer) {
        return R.layout.news_footer;

    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean item) {
        helper.setText(R.id.tvTitle,item.getTitle())
                .setText(R.id.tvDesc,item.getDigest());
        Glide.with(mContext).load(item.getImgsrc()).into((ImageView) helper.getView(R.id.ivNews));
    }
}
