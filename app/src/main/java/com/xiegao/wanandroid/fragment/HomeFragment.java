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

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kelin.banner.view.BannerView;
import com.kelin.banner.view.PointIndicatorView;
import com.xiegao.wanandroid.HttpUtils.bean.BaseData;
import com.xiegao.wanandroid.HttpUtils.interceptor.Transformer;
import com.xiegao.wanandroid.HttpUtils.observer.DataObserver;
import com.xiegao.wanandroid.R;
import com.xiegao.wanandroid.api.ApiHelper;
import com.xiegao.wanandroid.base.BaseFragment;
import com.xiegao.wanandroid.bean.BannerBean;
import com.xiegao.wanandroid.view.ImageBannerEntry;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by XIE on 2019/4/15.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.vp_view_pager)
    BannerView vpViewPager;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_sub_title)
    TextView tvSubTitle;
    @BindView(R.id.biv_indicator)
    PointIndicatorView bivIndicator;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        ApiHelper.getWanAndroidApi()
                .getBanner()
                .compose(Transformer.<BaseData<List<BannerBean>>>switchSchedulers(loading_dialog))
                .subscribe(new DataObserver<List<BannerBean>>() {
                    @Override
                    protected void onError(String errorMsg) {

                    }

                    @Override
                    protected void onSuccess(List<BannerBean> data) {
//                        List<ImageBannerEntry> bannerEntries = data();
                        //设置数据源并开始轮播。如果不希望启动轮播则调用两个参数的方法：bannerView.setEntries(bannerEntries, false); 你也可以通过bannerView.start();的方式启动轮播。
//                        vpViewPager.setEntries(bannerEntries);

                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
