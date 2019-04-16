package com.xiegao.wanandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebView;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;
import com.xiegao.wanandroid.R;
import com.xiegao.wanandroid.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

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
 * Created by XIE on 2019/4/16.
 */

public class WebActivity extends BaseActivity {
    @BindView(R.id.webView)
    WebView webView;
    private String weburl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        weburl= getIntent().getStringExtra("weburl");
        initView();
    }


    private void initView(){

        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptEnabled(true);

        //加上这些代码 就能正常使用h5
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setSavePassword(true);
        webView.getSettings().setSaveFormData(true);
        webView.getSettings().setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");
        webView.getSettings().setDomStorageEnabled(true);
        webView.requestFocus();
        webView.loadUrl(weburl);
        webView.setWebViewClient(new android.webkit.WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                webView.goBack();
                return true;
            } else {
               finish();
                return true;
            }

        }
        return false;
    }
}
