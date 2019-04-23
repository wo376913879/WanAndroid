package com.xiegao.wanandroid.adapter;
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
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.xiegao.wanandroid.bean.OfficalAccontsBean;
import com.xiegao.wanandroid.bean.ProjectBean;
import com.xiegao.wanandroid.fragment.OfficalAccontsChildFragment;
import com.xiegao.wanandroid.fragment.ProjectChildFragment;

import java.util.List;

/**
 * Created by XIE on 2019/4/17.
 */

public class OfficalAccontsAdapet extends FragmentPagerAdapter {


    private List<OfficalAccontsBean.DataBean> mTitles;
    private Context context;



    public OfficalAccontsAdapet(FragmentManager fm, Context context, List<OfficalAccontsBean.DataBean> mTitles) {
        super(fm);
        this.context = context;
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return new OfficalAccontsChildFragment(context, mTitles.get(position).getId());
    }
    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position).getName();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }
}
