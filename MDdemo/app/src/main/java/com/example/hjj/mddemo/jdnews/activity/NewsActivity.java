package com.example.hjj.mddemo.jdnews.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hjj.mddemo.R;
import com.example.hjj.mddemo.databinding.ActivityNewsBinding;
import com.example.hjj.mddemo.jdnews.adapter.ChannelAdapter;
import com.example.hjj.mddemo.jdnews.adapter.NewsAdapter;
import com.example.hjj.mddemo.jdnews.base.BaseActivity;
import com.example.hjj.mddemo.jdnews.constants.Myconfig;
import com.example.hjj.mddemo.jdnews.events.ChannelEvent;
import com.example.hjj.mddemo.jdnews.helper.MyDecoration;
import com.example.hjj.mddemo.jdnews.view.IChannelView;
import com.example.hjj.mddemo.jdnews.view.INewsView;
import com.example.hjj.mddemo.jdnews.viewmodel.ChannelVM;
import com.example.hjj.mddemo.jdnews.viewmodel.NewsVM;
import com.example.hjj.mddemo.mvvm.helper.DialogHelper;
import com.example.hjj.mddemo.mvvm.utils.ToastUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class NewsActivity extends BaseActivity implements INewsView, XRecyclerView.LoadingListener, IChannelView {
    private Context context;
    private ActivityNewsBinding binding;
    private NewsAdapter adapter;
    private NewsVM newsVM;
    private ChannelVM channelVM;
    private ChannelAdapter channelAdapter;
    private String channel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news);
        context = this;
        initRecyclerView();
        channelVM = new ChannelVM(this, channelAdapter);
        newsVM = new NewsVM(this, adapter);
        EventBus.getDefault().register(this);

    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        binding.newsRecy.setRefreshProgressStyle(ProgressStyle.BallClipRotate);//设置下拉刷新的样式
        binding.newsRecy.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);//设置上拉加载更多的样式
        binding.newsRecy.setArrowImageView(R.mipmap.pull_down_arrow);
        binding.newsRecy.setLoadingListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.newsRecy.setLayoutManager(layoutManager);
        adapter = new NewsAdapter(this);
        binding.newsRecy.setAdapter(adapter);
        channelAdapter = new ChannelAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.channelRecy.setLayoutManager(linearLayoutManager);
        binding.channelRecy.setAdapter(channelAdapter);
        binding.channelRecy.addItemDecoration(new MyDecoration(this, MyDecoration.HORIZONTAL_LIST));

    }

    /**
     * 开始加载
     *
     * @param loadType 加载的类型 0：第一次记载 1：下拉刷新 2：上拉加载更多
     */
    @Override
    public void loadStart(int loadType) {
        if (loadType == Myconfig.FIRST_LOAD) {
            DialogHelper.getInstance().show(context, "加载中...");
        }

    }

    /**
     * 加载完成
     */
    @Override
    public void loadComplete() {
        DialogHelper.getInstance().close();
        binding.newsRecy.loadMoreComplete();
        binding.newsRecy.refreshComplete();
    }

    /**
     * 加载失败
     *
     * @param message
     */
    @Override
    public void loadFailure(String message) {
        DialogHelper.getInstance().close();
        binding.newsRecy.loadMoreComplete();
        binding.newsRecy.refreshComplete();
        ToastUtils.show(context, message);
    }

    @Override
    public void onRefresh() {
        newsVM.refreshNewsData(channel);
    }

    @Override
    public void onLoadMore() {
        newsVM.loadMore(channel);
    }

    @Subscribe
    public void onChannelEvent(ChannelEvent event) {
        channel = event.message;
        newsVM.getNewsData(channel);
    }
}
