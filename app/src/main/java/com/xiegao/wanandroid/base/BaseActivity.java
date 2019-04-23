package com.xiegao.wanandroid.base;
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

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xiegao.wanandroid.HttpUtils.interfaces.ILoadingView;
import com.xiegao.wanandroid.utils.LogUtil;
import com.xiegao.wanandroid.widget.LoadingDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Activity的基类
 * Created by XIE on 2019/4/15.
 */
public class BaseActivity extends AppCompatActivity {
    protected String TAG = getClass().getName();
    protected ILoadingView loading_dialog;
    protected Activity baseActivity=this;
//    protected ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i(TAG +"  ================  onCreate");
        loading_dialog = new LoadingDialog(baseActivity);


    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.i(TAG +"  ================  onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.i(TAG +"  ================  onResume");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.i(TAG +"  ================  onNewIntent");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.i(TAG +"  ================  onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG +"  ================  onDestroy");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.i(TAG +"  ================  onActivityResult");
    }


}


