package com.xiegao.wanandroid;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.xiegao.wanandroid.HttpUtils.bean.BaseData;
import com.xiegao.wanandroid.HttpUtils.interceptor.Transformer;
import com.xiegao.wanandroid.HttpUtils.observer.DataObserver;
import com.xiegao.wanandroid.api.ApiHelper;
import com.xiegao.wanandroid.base.BaseActivity;
import com.xiegao.wanandroid.bean.BannerBean;
import com.xiegao.wanandroid.fragment.HomeFragment;
import com.xiegao.wanandroid.fragment.OfficalAccontsFragment;
import com.xiegao.wanandroid.fragment.ProjectFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;


public class MainActivity extends BaseActivity {
    @BindView(R.id.mainview)
    FrameLayout mainview;
    @BindView(R.id.bnv)
    BottomNavigationView bnv;

    private HomeFragment fragment1;
    private ProjectFragment fragment2;
    private OfficalAccontsFragment fragment3;
    private Fragment[] fragments;
    private int lastfragment;//用于记录上个选择的Fragment


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        textView= findViewById(R.id.textwwww);
        initFragment();

    }
    private void initFragment(){
        fragment1 = new HomeFragment();
        fragment2 = new ProjectFragment();
        fragment3 = new OfficalAccontsFragment();
        fragments = new Fragment[]{fragment1,fragment2,fragment3};
        lastfragment=0;
        getSupportFragmentManager().beginTransaction().replace(R.id.mainview,fragment1).show(fragment1).commit();

        bnv.setOnNavigationItemSelectedListener(changeFragment);
    }
    //判断选择的菜单
    private BottomNavigationView.OnNavigationItemSelectedListener changeFragment= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId())
            {
                case R.id.home:
                {
                    if(lastfragment!=0)
                    {
                        switchFragment(lastfragment,0);
                        lastfragment=0;

                    }

                    return true;
                }
                case R.id.project:
                {
                    if(lastfragment!=1)
                    {
                        switchFragment(lastfragment,1);
                        lastfragment=1;

                    }

                    return true;
                }
                case R.id.offical_accounts:
                {
                    if(lastfragment!=2)
                    {
                        switchFragment(lastfragment,2);
                        lastfragment=2;

                    }

                    return true;
                }


            }


            return false;
        }
    };
    //切换Fragment
    private void switchFragment(int lastfragment,int index){
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastfragment]);//隐藏上个Fragment
        if(fragments[index].isAdded()==false)
        {
            transaction.add(R.id.mainview,fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();


    }
}
