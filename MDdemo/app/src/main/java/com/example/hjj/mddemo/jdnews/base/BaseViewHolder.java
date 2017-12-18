package com.example.hjj.mddemo.jdnews.base;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by 黄俊杰 on 2017-12-16.
 */

public class BaseViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {
    /**
     * 视图绑定
     */
    private B mBinding;

    public BaseViewHolder(B binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public B getmBinding() {
        return mBinding;
    }
}
