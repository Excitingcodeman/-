package com.example.hjj.mddemo.jdnews.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import com.example.hjj.mddemo.BR;
import com.example.hjj.mddemo.R;
import com.example.hjj.mddemo.jdnews.base.BaseAdapter;
import com.example.hjj.mddemo.jdnews.base.BaseViewHolder;
import com.example.hjj.mddemo.jdnews.beans.beanhelper.ChannelBeanHelper;
import com.example.hjj.mddemo.jdnews.events.ChannelEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by 黄俊杰 on 2017-12-18.
 */

public class ChannelAdapter extends BaseAdapter<ChannelBeanHelper, BaseViewHolder> {


    public ChannelAdapter(Context mContext) {
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
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.channel_item, parent, false);
        return new BaseViewHolder(binding);
    }

    /**
     * holder 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    protected void onBindVH(BaseViewHolder holder, int position) {
        ViewDataBinding dataBinding = holder.getmBinding();
        dataBinding.setVariable(BR.channelBeanHelper, mList.get(position));
        dataBinding.setVariable(BR.adapter, this);
        dataBinding.setVariable(BR.position, position);
        dataBinding.executePendingBindings();
    }

    /***
     * 单击事件
     * @param channelBeanHelper
     * @param position
     */
    public void onclick(ChannelBeanHelper channelBeanHelper, int position) {
        EventBus.getDefault().post(new ChannelEvent(channelBeanHelper.channel.get()));
    }


}
