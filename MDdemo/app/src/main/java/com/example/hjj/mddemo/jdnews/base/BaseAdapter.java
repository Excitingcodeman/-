package com.example.hjj.mddemo.jdnews.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黄俊杰 on 2017-12-16.
 * 基类的适配器
 */

public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    public Context mContext;
    public List<T> mList;//数据源
    public LayoutInflater inflater;

    public BaseAdapter(Context mContext) {
        this.mContext = mContext;
        this.mList = new ArrayList<>();
        inflater = (LayoutInflater) mContext.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateVH(parent, viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        onBindVH(holder, position);
    }


    /**
     * 创建 View Holder
     *
     * @param parent
     * @param viewType
     * @return
     */
    protected abstract VH onCreateVH(ViewGroup parent, int viewType);

    /**
     * holder 绑定数据
     *
     * @param holder
     * @param position
     */
    protected abstract void onBindVH(VH holder, int position);

    /**
     * 刷新数据
     *
     * @param data
     */
    public void refreshData(List<T> data) {
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 加载更多
     *
     * @param data 加载的新的数据
     */
    public void loadMoreData(List<T> data) {
        mList.addAll(data);
        notifyDataSetChanged();
    }
}
