package com.xiegao.wanandroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiegao.wanandroid.HttpUtils.interceptor.Transformer;
import com.xiegao.wanandroid.HttpUtils.observer.DataObserver;
import com.xiegao.wanandroid.R;
import com.xiegao.wanandroid.adapter.OfficalAccontsAdapet;
import com.xiegao.wanandroid.adapter.ProjectPagerAdapet;
import com.xiegao.wanandroid.api.ApiHelper;
import com.xiegao.wanandroid.base.BaseFragment;
import com.xiegao.wanandroid.bean.OfficalAccontsBean;
import com.xiegao.wanandroid.bean.ProjectBean;
import com.xiegao.wanandroid.view.JSTabLayout;

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
 * Created by XIE on 2019/4/15.
 */

public class OfficalAccontsFragment extends BaseFragment {
    @BindView(R.id.tabLayout)
    JSTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;
    private OfficalAccontsAdapet accontsAdapet;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_offical_accounts, container, false);
        }
        unbinder = ButterKnife.bind(this, rootView);
        initgetOfficalAccontsBeanData();
        return rootView;
    }
    private void initgetOfficalAccontsBeanData() {
        ApiHelper.getWanAndroidApi()
                .getOfficalAccontsBeanData()
                .compose(Transformer.<OfficalAccontsBean>switchSchedulers(loading_dialog))
                .subscribe(new DataObserver<OfficalAccontsBean>() {
                    @Override
                    protected void onError(String errorMsg) {

                    }

                    @Override
                    protected void onSuccess(OfficalAccontsBean data) {
//                        List<ProjectBean.DataBean> dataBeans=new ArrayList<>();
//                        dataBeans.add((ProjectBean.DataBean) data.getData());
                        accontsAdapet = new OfficalAccontsAdapet(getChildFragmentManager(),getContext(), data.getData());
                        viewPager.setAdapter(accontsAdapet);
//                        tabLayout.setUpWithViewPager(viewPager);
                        tabLayout.setupWithViewPager(viewPager);

                    }
                });

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
