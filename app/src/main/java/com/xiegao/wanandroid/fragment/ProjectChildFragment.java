package com.xiegao.wanandroid.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xiegao.wanandroid.HttpUtils.interceptor.Transformer;
import com.xiegao.wanandroid.HttpUtils.observer.DataObserver;
import com.xiegao.wanandroid.R;
import com.xiegao.wanandroid.activity.WebActivity;
import com.xiegao.wanandroid.adapter.ArticleAdapter;
import com.xiegao.wanandroid.adapter.ProjectListAdapet;
import com.xiegao.wanandroid.api.ApiHelper;
import com.xiegao.wanandroid.base.ViewPagerFragment;
import com.xiegao.wanandroid.bean.ArticleBean;
import com.xiegao.wanandroid.bean.ProjectListBean;
import com.zhouwei.mzbanner.MZBannerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃  神兽保佑
 * 　　　　┃　　　┃  代码无bug
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 */

/**
 * Created by XIE on 2019/4/17.
 */

@SuppressLint("ValidFragment")
public class ProjectChildFragment extends ViewPagerFragment {
    @BindView(R.id.MZBbanner)
    MZBannerView MZBbanner;
    @BindView(R.id.xrecyclervire)
    RecyclerView XRecyclervire;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    Unbinder unbinder;
    private int cid;
    private Context context;
    private ProjectListAdapet mAdapter;
    private static final int PAGE_SIZE = 20;
    @SuppressLint("ValidFragment")
    public ProjectChildFragment(Context context, int cid) {
        this.cid = cid;
        this.context = context;
    }

    private int index = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_home, container, false);

        }
        unbinder = ButterKnife.bind(this, rootView);
        MZBbanner.setVisibility(View.GONE);

        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        XRecyclervire.setLayoutManager(new LinearLayoutManager(getContext()));
        mSwipeRefreshLayout.setRefreshing(true);

        mAdapter = new ProjectListAdapet();
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.setPreLoadNumber(3);
//        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        XRecyclervire.setAdapter(mAdapter);

        XRecyclervire.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
            }
        });

        initRefreshLayout();
        return rootView;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible) {
            initgetProjectListData(true,cid);
        } else {
            //        setRefresh(false);
        }
    }
    private void initRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }
    private void refresh() {
        index = 0;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        initgetProjectListData(true,cid);
    }
    private void loadMore() {
        initgetProjectListData(false,cid);
    }

    private void initgetProjectListData(final boolean isRefresh,int cid) {
        ApiHelper.getWanAndroidApi()
//                .getProjectListData(index, cid)
                .getProjectListData()
                .compose(Transformer.<ProjectListBean>switchSchedulers())
                .subscribe(new DataObserver<ProjectListBean>() {
                    @Override
                    protected void onError(String errorMsg) {

                    }

                    @Override
                    protected void onSuccess(final ProjectListBean data) {
                        setData(isRefresh, data.getData().getDatas());

                        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent=new Intent(getContext(), WebActivity.class);
                                intent.putExtra("weburl",data.getData().getDatas().get(position).getLink());
                                startActivity(intent);
                            }
                        });

                        mAdapter.setEnableLoadMore(true);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });

    }
    private void setData(boolean isRefresh, List<ProjectListBean.DataBean.DatasBean> data) {
        index++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
//            Toast.makeText(this, "no more data", Toast.LENGTH_SHORT).show();
        } else {
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
