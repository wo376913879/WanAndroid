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
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiegao.wanandroid.HttpUtils.interceptor.Transformer;
import com.xiegao.wanandroid.HttpUtils.observer.DataObserver;
import com.xiegao.wanandroid.R;
import com.xiegao.wanandroid.adapter.ProjectPagerAdapet;
import com.xiegao.wanandroid.api.ApiHelper;
import com.xiegao.wanandroid.base.BaseFragment;
import com.xiegao.wanandroid.bean.ProjectBean;
import com.xiegao.wanandroid.view.JSTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;

/**
 * Created by XIE on 2019/4/15.
 */

public class ProjectFragment extends BaseFragment {


    @BindView(R.id.tabLayout)
    JSTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;
    private ProjectPagerAdapet projectPagerAdapet;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        unbinder = ButterKnife.bind(this, view);
        initgetProjectData();
        return view;

    }

    private void initgetProjectData() {
        ApiHelper.getWanAndroidApi()
                .getProjectData()
                .compose(Transformer.<ProjectBean>switchSchedulers(loading_dialog))
                .subscribe(new DataObserver<ProjectBean>() {
                    @Override
                    protected void onError(String errorMsg) {

                    }

                    @Override
                    protected void onSuccess(ProjectBean data) {
//                        List<ProjectBean.DataBean> dataBeans=new ArrayList<>();
//                        dataBeans.add((ProjectBean.DataBean) data.getData());
                        projectPagerAdapet = new ProjectPagerAdapet(getChildFragmentManager(),getContext(), data.getData());
                        viewPager.setAdapter(projectPagerAdapet);
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
