package com.xiegao.wanandroid.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.itheima.roundedimageview.RoundedImageView;
import com.xiegao.wanandroid.HttpUtils.RxHttpUtils;
import com.xiegao.wanandroid.HttpUtils.interceptor.Transformer;
import com.xiegao.wanandroid.HttpUtils.observer.DataObserver;
import com.xiegao.wanandroid.HttpUtils.utils.ToastUtils;
import com.xiegao.wanandroid.R;
import com.xiegao.wanandroid.api.WanAndroidApi;
import com.xiegao.wanandroid.base.BaseFragment;
import com.xiegao.wanandroid.bean.LonginBean;
import com.xiegao.wanandroid.bean.RegisterBean;
import com.xiegao.wanandroid.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;
import static com.xiegao.wanandroid.MyApplication.LOGIN_FLAG;

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
    @BindView(R.id.edit_confirm_password)
    EditText editConfirmPassword;
    @BindView(R.id.roundedImageView)
    RoundedImageView roundedImageView;
    @BindView(R.id.text_name)
    TextView textName;

    private AlertDialog alertDialog;


    private boolean registered = false;
    private SharedPreferences.Editor editor;
    private  SharedPreferences sharedPreferences;
    private String Username;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_mine, container, false);
            sharedPreferences = getContext().getSharedPreferences(LOGIN_FLAG, MODE_PRIVATE);
            Username= sharedPreferences.getString("username", "");
        }
        unbinder = ButterKnife.bind(this, rootView);


        if (TextUtils.isEmpty(Username)) {
            notLoggedIn.setVisibility(View.VISIBLE);
            loggedIn.setVisibility(View.GONE);
        }else {
            notLoggedIn.setVisibility(View.GONE);
            loggedIn.setVisibility(View.VISIBLE);
            textName.setText(Username);
        }


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
                if (registered) {
                    if (TextUtils.isEmpty(editText6.getText().toString()) || TextUtils.isEmpty(editPassword.getText().toString()) || TextUtils.isEmpty(editConfirmPassword.getText().toString())) {
                        ToastUtils.showToast("用户名或密码不能为空");
                    } else {
                        if (editPassword.getText().toString().equals(editConfirmPassword.getText().toString())) {
                            RxHttpUtils
                                    .createApi(WanAndroidApi.class)
                                    .getRegister(editText6.getText().toString(), editPassword.getText().toString(), editConfirmPassword.getText().toString())
                                    .compose(Transformer.<RegisterBean>switchSchedulers())
                                    .subscribe(new DataObserver<RegisterBean>() {
                                        @Override
                                        protected void onError(String errorMsg) {
                                            ToastUtils.showToast(errorMsg);
                                        }

                                        @Override
                                        protected void onSuccess(final RegisterBean data) {
                                            LogUtil.i("注册", new Gson().toJson(data));
                                            if (data.getErrorCode() == 0) {

                                            } else {
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

                        } else {
                            ToastUtils.showToast("两次输入的密码不一致");
                        }
                    }
                } else {
                    if (TextUtils.isEmpty(editText6.getText().toString()) || TextUtils.isEmpty(editPassword.getText().toString())) {
                        ToastUtils.showToast("用户名或密码不能为空");
                    } else {
                        RxHttpUtils
                                .createApi(WanAndroidApi.class)
                                .getLogin(editText6.getText().toString(), editPassword.getText().toString())
                                .compose(Transformer.<LonginBean>switchSchedulers())
                                .subscribe(new DataObserver<LonginBean>() {
                                    @Override
                                    protected void onError(String errorMsg) {
                                        ToastUtils.showToast(errorMsg);
                                    }

                                    @Override
                                    protected void onSuccess(final LonginBean data) {
                                        LogUtil.i("登录", new Gson().toJson(data));
                                        if (data.getErrorCode() == 0) {
                                            notLoggedIn.setVisibility(View.GONE);
                                            loggedIn.setVisibility(View.VISIBLE);
                                            editor = sharedPreferences.edit();
                                            editor.putString("username", data.getData().getUsername());
                                            editor.commit();
                                            textName.setText(data.getData().getUsername());

                                        } else {
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
                    }
                }
                break;
            case R.id.text_registered:
                if (registered) {
                    registered = false;
                    textRegistered.setText(R.string.registered);
                    butLogin.setText(R.string.login);
                    editConfirmPassword.setVisibility(View.GONE);
                } else {
                    registered = true;
                    textRegistered.setText(R.string.login);
                    butLogin.setText(R.string.registered);
                    editConfirmPassword.setVisibility(View.VISIBLE);
                }

                editText6.setText("");
                editPassword.setText("");
                editConfirmPassword.setText("");
                break;
        }
    }
}
