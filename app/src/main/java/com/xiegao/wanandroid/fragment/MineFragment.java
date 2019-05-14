package com.xiegao.wanandroid.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.xiegao.wanandroid.HttpUtils.RxHttpUtils;
import com.xiegao.wanandroid.HttpUtils.interceptor.Transformer;
import com.xiegao.wanandroid.HttpUtils.observer.DataObserver;
import com.xiegao.wanandroid.MainActivity;
import com.xiegao.wanandroid.R;
import com.xiegao.wanandroid.activity.WebActivity;
import com.xiegao.wanandroid.api.ApiHelper;
import com.xiegao.wanandroid.api.WanAndroidApi;
import com.xiegao.wanandroid.base.BaseFragment;
import com.xiegao.wanandroid.bean.LonginBean;
import com.xiegao.wanandroid.bean.ProjectListBean;
import com.xiegao.wanandroid.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.RequestBody;

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
 * Created by XIE on 2019/5/13.
 */

public class MineFragment extends BaseFragment {
    @BindView(R.id.but_login)
    Button butLogin;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.editText6)
    EditText editText6;
    @BindView(R.id.text_registered)
    TextView textRegistered;
    @BindView(R.id.not_logged_in)
    ConstraintLayout notLoggedIn;
    @BindView(R.id.logged_in)
    ConstraintLayout loggedIn;
    Unbinder unbinder;

    private AlertDialog alertDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_mine, container, false);
        }
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible) {
            //第一次进入
        } else {
            //再次进入

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.but_login, R.id.text_registered})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.but_login:
                HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("username", editText6.getText().toString());
                        hashMap.put("password", editPassword.getText().toString());
//                JSONObject obj = new JSONObject();
//                try {
//                    obj.put("username", editText6.getText().toString());
//                    obj.put("password", editPassword.getText().toString());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                RequestBody body = RequestBody.create(MediaType.parse("application/json"), obj.toString());

                RxHttpUtils
                        .createApi(WanAndroidApi.class)
//                        .getLogin(hashMap)
                        .getLogin(editText6.getText().toString(),editPassword.getText().toString())
                        .compose(Transformer.<LonginBean>switchSchedulers())
                        .subscribe(new DataObserver<LonginBean>() {
                            @Override
                            protected void onError(String errorMsg) {

                            }

                            @Override
                            protected void onSuccess(final LonginBean data) {
                                LogUtil.i("登录",new Gson().toJson(data));
                                if (data.getErrorCode()==0) {

                                }else {
                                    alertDialog = new AlertDialog.Builder(getContext())
                                            .setMessage(data.getErrorMsg())
                                    .setCancelable(false)
                                    .setTitle("提示").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            alertDialog.dismiss();
                                        }
                                    }).create();
                                        alertDialog.show();
                                }

                            }
                        });

                break;
            case R.id.text_registered:
                break;
        }
    }
}
