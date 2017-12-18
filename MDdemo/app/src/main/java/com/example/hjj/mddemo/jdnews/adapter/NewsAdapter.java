package com.example.hjj.mddemo.jdnews.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import com.example.hjj.mddemo.BR;
import com.example.hjj.mddemo.R;
import com.example.hjj.mddemo.jdnews.activity.NewsDetailActivity;
import com.example.hjj.mddemo.jdnews.base.BaseAdapter;
import com.example.hjj.mddemo.jdnews.base.BaseViewHolder;
import com.example.hjj.mddemo.jdnews.beans.beanhelper.NewsBeanHelper;
import com.example.hjj.mddemo.mvvm.utils.ToastUtils;

/**
 * Created by 黄俊杰 on 2017-12-18.
 */

public class NewsAdapter extends BaseAdapter<NewsBeanHelper, BaseViewHolder> {
    public NewsAdapter(Context mContext) {
        super(mContext);
    }

    /**
     * 创建 View Holder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    protected BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.news_item, parent, false);
        return new BaseViewHolder(dataBinding);
    }

    /**
     * holder 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    protected void onBindVH(BaseViewHolder holder, int position) {
        ViewDataBinding binding = holder.getmBinding();
        binding.setVariable(BR.newsBeanHelper, mList.get(position));
        binding.setVariable(BR.position, position);
        binding.setVariable(BR.adapter, this);
        binding.executePendingBindings();//防止闪烁
    }

    /**
     * 查看新闻详情
     */
    public void detail(NewsBeanHelper beanHelper, int position) {
        Intent intent = new Intent(mContext, NewsDetailActivity.class);
        intent.putExtra("detail", beanHelper.url.get());
        mContext.startActivity(intent);
    }
}
