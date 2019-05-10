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
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiegao.wanandroid.R;
import com.xiegao.wanandroid.activity.WebActivity;
import com.xiegao.wanandroid.bean.ArticleBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by XIE on 2019/4/16.
 */
////
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

//public class ArticleAdapter extends RecyclerAdapter<ArticleBean.DataBean.DatasBean> {
//    private Context context;
//
//    public ArticleAdapter(Context context) {
//        super(context);
//        this.context = context;
//    }
//
//    @Override
//    public BaseViewHolder<ArticleBean.DataBean.DatasBean> onCreateBaseViewHolder(ViewGroup parent, int viewType) {
//        return new ArticleHolder(parent, context);
//    }
//
//
//    class ArticleHolder extends BaseViewHolder<ArticleBean.DataBean.DatasBean> {
//
//
////        @BindView(R.id.article_title)
//        TextView articleTitle;
////        @BindView(R.id.article_nice_date)
//        TextView articleNiceDate;
////        @BindView(R.id.item_img_layout)
//        CardView itemImgLayout;
//        @Override
//        public void onInitializeView() {
//            super.onInitializeView();
//            articleTitle = findViewById(R.id.article_title);
//            articleNiceDate = findViewById(R.id.article_nice_date);
//
//        }
//
//        private Context context;
//
//        public ArticleHolder(ViewGroup parent, Context context) {
//            super(parent, R.layout.layout_article);
//            this.context = context;
//        }
//
//        @Override
//        public void setData(final ArticleBean.DataBean.DatasBean object) {
//            super.setData(object);
//            articleTitle.setText(object.getTitle());
//            articleTitle.setText(object.getNiceDate());
//
//        }
//
//
//
//        @Override
//        public void onItemViewClick(ArticleBean.DataBean.DatasBean object) {
//            super.onItemViewClick(object);
//            Intent intent = new Intent(getContext(), WebActivity.class);
////
//                                intent.putExtra("weburl", object.getLink());
////                                EventBus.getDefault().post(new EventBusStringBean(data.getData().getDatas().get(position).getLink()));
//                                context.startActivity(intent);
//
//        }
//    }
//}
