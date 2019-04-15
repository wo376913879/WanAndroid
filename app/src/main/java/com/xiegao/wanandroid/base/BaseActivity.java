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

/**
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
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("加载中...");
//        progressDialog.setCancelable(false);

    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.i(TAG +"  ================  onStart");
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("加载中...");
    }

//    public void showProgressDialog() {
//        if (progressDialog != null) {
//            progressDialog.show();
//        }
//    }
//    public void hideProgressDialog() {
//        progressDialog.dismiss();
//    }

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

    protected void setErrorMsg(String msg){
        //Toast.makeText(this, "Error: "+msg, Toast.LENGTH_SHORT).show();
    }
}


