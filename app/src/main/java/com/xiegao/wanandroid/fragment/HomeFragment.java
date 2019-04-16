package com.xiegao.wanandroid.fragment;
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

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xiegao.wanandroid.HttpUtils.bean.BaseData;
import com.xiegao.wanandroid.HttpUtils.interceptor.Transformer;
import com.xiegao.wanandroid.HttpUtils.observer.DataObserver;
import com.xiegao.wanandroid.R;
import com.xiegao.wanandroid.activity.WebActivity;
import com.xiegao.wanandroid.adapter.ArticleAdapter;
import com.xiegao.wanandroid.api.ApiHelper;
import com.xiegao.wanandroid.base.BaseFragment;
import com.xiegao.wanandroid.bean.ArticleBean;
import com.xiegao.wanandroid.bean.BannerBean;
import com.xiegao.wanandroid.utils.LogUtil;
import com.xiegao.wanandroid.utils.XRecyclerViewUtil;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by XIE on 2019/4/15.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.MZBbanner)
    MZBannerView mMZBanner;

//    @BindView(R.id.xrecyclervire)
    RecyclerView XRecyclervire;

//    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    Unbinder unbinder;
    private int index = 0;
    private static final int PAGE_SIZE = 20;
    private ArticleAdapter mAdapter;
    private List<ArticleBean.DataBean.DatasBean> articleBeanList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        XRecyclervire=view.findViewById(R.id.xrecyclervire);
        mSwipeRefreshLayout=view.findViewById(R.id.swipeLayout);
//        XRecyclerViewUtil.xrvSetVertical(getContext(), XRecyclervire, true, true);

        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        XRecyclervire.setLayoutManager(new LinearLayoutManager(getContext()));
        mSwipeRefreshLayout.setRefreshing(true);

        mAdapter = new ArticleAdapter();
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
//        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setPreLoadNumber(3);
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        XRecyclervire.setAdapter(mAdapter);

        XRecyclervire.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
            }
        });



        initBannerData();
        initArticleData();
        initRefreshLayout();
//        refresh();
        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        MultiTypeAdapter adapter = new MultiTypeAdapter(recyclerView);


    }

    private void initBannerData() {
        ApiHelper.getWanAndroidApi()
                .getBanner()
                .compose(Transformer.<BannerBean>switchSchedulers(loading_dialog))
                .subscribe(new DataObserver<BannerBean>() {
                    @Override
                    protected void onError(String errorMsg) {

                    }

                    @Override
                    protected void onSuccess(final BannerBean data) {
                        mMZBanner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
                            @Override
                            public void onPageClick(View view, int position) {
                                Intent intent=new Intent(getContext(), WebActivity.class);
                                intent.putExtra("weburl",data.getData().get(position).getUrl());
                                startActivity(intent);

                            }
                        });

                        mMZBanner.addPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {
                                Log.e("zw", "onPageScrolled---->" + position);
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });

                        mMZBanner.setPages(data.getData(), new MZHolderCreator<BannerViewHolder>() {
                            @Override
                            public BannerViewHolder createViewHolder() {
                                return new BannerViewHolder();
                            }
                        });
                        mMZBanner.start();
                    }
                });
    }

    private void initArticleData() {
        ApiHelper.getWanAndroidApi()
                .getarticleStringData(index)
                .compose(Transformer.<ArticleBean>switchSchedulers())
                .subscribe(new DataObserver<ArticleBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        mAdapter.setEnableLoadMore(true);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    protected void onSuccess(final ArticleBean data) {
                        LogUtil.e("ces",data.toString());
//                        articleBeanList.add(data.getData().getDatas());
                        setData(true, data.getData().getDatas());

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


    private void initRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void refresh() {
        index = 1;

        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        initArticleData();
    }
    private void loadMore() {
        initArticleData();
    }

    private void setData(boolean isRefresh, List<ArticleBean.DataBean.DatasBean> data) {
        index++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            articleBeanList=new ArrayList<>();
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                articleBeanList.addAll(data);
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

    @Override
    public void onPause() {
        super.onPause();
        mMZBanner.pause();//暂停轮播
    }

    @Override
    public void onResume() {
        super.onResume();
        mMZBanner.start();//开始轮播
    }

}

class BannerViewHolder implements MZViewHolder<BannerBean.DataBean> {
    private ImageView mImageView;

    @Override
    public View createView(Context context) {
        // 返回页面布局
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
        mImageView = (ImageView) view.findViewById(R.id.banner_image);
        return view;
    }

    @Override
    public void onBind(Context context, int position, BannerBean.DataBean data) {
        // 数据绑定
        Glide.with(context)
                .load(data.getImagePath())
                .into(mImageView);
//        mImageView.setImageResource(data);
    }
}