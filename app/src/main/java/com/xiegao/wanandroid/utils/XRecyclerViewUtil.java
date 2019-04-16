package com.xiegao.wanandroid.utils;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.GridLayout;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by wangxiao on 2018/12/17.
 */

public class XRecyclerViewUtil {


//    public static void xrvSetVertical(Context context, XRecyclerView xRecyclerView) {
//        xrvSetVertical(context, xRecyclerView, false, false);
//    }

    public static void xrvSetVertical(Context context, XRecyclerView xRecyclerView, boolean pullRefreshEnabled, boolean loadingMoreEnabled) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(linearLayoutManager);
        xRecyclerView.setPullRefreshEnabled(pullRefreshEnabled);     //是否允许下拉刷新
        xRecyclerView.setLoadingMoreEnabled(loadingMoreEnabled);     //是否允许上拉加载

        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallZigZag); //设定下拉刷新样式
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallZigZag);//设定上拉加载样式
//        xRecyclerView.setNestedScrollingEnabled(false); //禁止滑动
//        xRecyclerView.setHasFixedSize(true);
    }

//    public static void xrvSetGrid(Context context, XRecyclerView xRecyclerView) {
//        xrvSetGrid(context, xRecyclerView, 2);
//    }


    public static void xrvSetGrid(Context context, XRecyclerView xRecyclerView, int spanCount) {
        GridLayoutManager layout = new GridLayoutManager(context, spanCount);
        layout.setOrientation(GridLayout.VERTICAL);
        xRecyclerView.setLayoutManager(layout);
        xRecyclerView.setPullRefreshEnabled(false);
        xRecyclerView.setLoadingMoreEnabled(false);
        xRecyclerView.setNestedScrollingEnabled(false); //禁止滑动

        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallZigZag); //设定下拉刷新样式
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallZigZag);//设定上拉加载样式
    }


}
