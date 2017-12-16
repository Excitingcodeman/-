package com.example.hjj.mddemo.mvvm.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;

import com.example.hjj.mddemo.R;
import com.example.hjj.mddemo.databinding.ActivityMain2Binding;
import com.example.hjj.mddemo.mvvm.adapter.NewsAdapter;
import com.example.hjj.mddemo.mvvm.helper.DialogHelper;
import com.example.hjj.mddemo.mvvm.utils.ToastUtils;
import com.example.hjj.mddemo.mvvm.view.INewsView;
import com.example.hjj.mddemo.mvvm.viewmodel.NewsVM;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import static com.example.hjj.mddemo.mvvm.constant.MainConstant.LoadData.FIRST_LOAD;

public class Main2Activity extends AppCompatActivity implements INewsView, XRecyclerView.LoadingListener {
    private Context context;
    private ActivityMain2Binding binding;

    private NewsAdapter newsAdapter;
    private NewsVM newsVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2);
//        setContentView(R.layout.activity_main2);
        context = this;
        initRecyclerView();
        newsVM = new NewsVM(this, newsAdapter);
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        binding.newsRv.setRefreshProgressStyle(ProgressStyle.BallClipRotate);//设置下拉刷新的样式
        binding.newsRv.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);//设置上拉加载更多的样式
        binding.newsRv.setArrowImageView(R.mipmap.pull_down_arrow);
        binding.newsRv.setLoadingListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.newsRv.setLayoutManager(layoutManager);
        newsAdapter = new NewsAdapter(this);
        binding.newsRv.setAdapter(newsAdapter);
    }

    @Override
    public void loadStart(int loadType) {
        if (loadType == FIRST_LOAD) {
            DialogHelper.getInstance().show(context, "加载中...");
        }
    }

    @Override
    public void loadComplete() {
        DialogHelper.getInstance().close();
        binding.newsRv.loadMoreComplete();
        binding.newsRv.refreshComplete();
    }

    @Override
    public void loadFailure(String message) {
        DialogHelper.getInstance().close();
        binding.newsRv.loadMoreComplete();
        binding.newsRv.refreshComplete();
        ToastUtils.show(context, message);
    }

    @Override
    public void onRefresh() {
        newsVM.loadRefreshData();
    }

    @Override
    public void onLoadMore() {
        newsVM.loadMoreData();
    }
}
