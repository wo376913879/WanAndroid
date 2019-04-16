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

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiegao.wanandroid.R;
import com.xiegao.wanandroid.bean.ArticleBean;

import java.util.List;

/**
 * Created by XIE on 2019/4/16.
 */

public class ArticleAdapter extends BaseQuickAdapter<ArticleBean.DataBean.DatasBean, BaseViewHolder> {
    public ArticleAdapter() {
        super(R.layout.layout_article, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean.DataBean.DatasBean item) {
        ((TextView) helper.getView(R.id.article_title)).setText(item.getTitle());
        ((TextView) helper.getView(R.id.article_nice_date)).setText(item.getNiceDate());

    }
}
