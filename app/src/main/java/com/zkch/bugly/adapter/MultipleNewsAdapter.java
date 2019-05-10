package com.zkch.bugly.adapter;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zkch.bugly.R;
import com.zkch.bugly.bean.NewsBean;

import java.util.List;

/**
 * 多条目 item adapter 适配器 须联合MultipleItem 使用
 *  在适配器构造函数里面addItemType绑定type和layout的关系
 */
public class MultipleNewsAdapter extends BaseMultiItemQuickAdapter<NewsBean, BaseViewHolder> {

    public MultipleNewsAdapter(List<NewsBean> data) {
        super(data);
        addItemType(NewsBean.TYPE_ITEM, R.layout.item_news);
        addItemType(NewsBean.TYPE_FOOTER,R.layout.news_footer);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean item) {
        helper.setText(R.id.tvTitle,item.getTitle())
              .setText(R.id.tvDesc,item.getDigest());
        Glide.with(mContext).load(item.getImgsrc()).into((ImageView) helper.getView(R.id.ivNews));
    }

}
