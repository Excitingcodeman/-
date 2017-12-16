package com.example.hjj.mddemo.mvvm.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import com.example.hjj.mddemo.BR;
import com.example.hjj.mddemo.R;
import com.example.hjj.mddemo.mvvm.base.BaseAdapter;
import com.example.hjj.mddemo.mvvm.base.BaseViewHolder;
import com.example.hjj.mddemo.mvvm.bean.SimpleNewsBean;
import com.example.hjj.mddemo.mvvm.utils.ToastUtils;

/**
 * Created by 黄俊杰 on 2017-12-16.
 */

public class NewsAdapter extends BaseAdapter<SimpleNewsBean, BaseViewHolder> {

    public NewsAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_news, parent, false);
        return new BaseViewHolder(dataBinding);
    }

    @Override
    protected void onBindVH(BaseViewHolder holder, int position) {
        ViewDataBinding binding = holder.getmBinding();
        binding.setVariable(BR.simpleNewsBean, mList.get(position));
        binding.setVariable(BR.position, position);
        binding.setVariable(BR.adapter, this);
        binding.executePendingBindings();//防止闪烁
    }

    public void clickDianZan(SimpleNewsBean simpleNewsBean, int position) {
        if (simpleNewsBean.isGood.get()) {
            simpleNewsBean.isGood.set(false);
            ToastUtils.show(mContext, "取消点赞 position=" + position);
        } else {
            simpleNewsBean.isGood.set(true);
            ToastUtils.show(mContext, "点赞成功 position=" + position);

        }
    }
}
