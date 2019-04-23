package com.xiegao.wanandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xiegao.wanandroid.base.BaseActivity;
import com.xiegao.wanandroid.fragment.HomeFragment;
import com.xiegao.wanandroid.fragment.OfficalAccontsFragment;
import com.xiegao.wanandroid.fragment.ProjectFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;


public class MainActivity extends BaseActivity {
    @BindView(R.id.mainview)
    FrameLayout mainview;
    @BindView(R.id.bnv)
    BottomNavigationView bnv;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.editText3)
    EditText editText3;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.button2)
    Button button2;
    private AlertDialog alertDialog;

    private HomeFragment fragment1;
    private ProjectFragment fragment2;
    private OfficalAccontsFragment fragment3;
    private Fragment[] fragments;
    private int lastfragment;//用于记录上个选择的Fragment
    private boolean isFirstRun;
    private ProgressDialog mProgressDialog;
    private SharedPreferences.Editor editor;
    private Dialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        bnv.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
//        SharedPreferences sharedPreferences = this.getSharedPreferences("share", MODE_PRIVATE);
//        isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
//        editor = sharedPreferences.edit();
////        textView= findViewById(R.id.textwwww);
////        initFragment();
//        if (!isFirstRun) {
//            editText2.setVisibility(View.GONE);
//            editText3.setVisibility(View.GONE);
//            button.setVisibility(View.GONE);
//            textView3.setVisibility(View.VISIBLE);
//            button2.setVisibility(View.VISIBLE);
//        } else {
//            alertDialog = new AlertDialog.Builder(MainActivity.this)
//                    .setMessage("新版本到达,是否更新")
//                    .setCancelable(false)
//                    .setTitle("更新提示")
//                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            alertDialog.dismiss();
//                        }
//                    })
//                    .setPositiveButton("更新升级", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            alertDialog.dismiss();
//                        }
//                    })
//                    .create();
//            alertDialog.show();
//        }
        initFragment();
    }

    private void initFragment() {
        fragment1 = new HomeFragment();
        fragment2 = new ProjectFragment();
        fragment3 = new OfficalAccontsFragment();
        fragments = new Fragment[]{fragment1, fragment2, fragment3};
        lastfragment = 0;
        getSupportFragmentManager().beginTransaction().replace(R.id.mainview, fragment1).show(fragment1).commit();

        bnv.setOnNavigationItemSelectedListener(changeFragment);
    }

    //判断选择的菜单
    private BottomNavigationView.OnNavigationItemSelectedListener changeFragment = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home: {
                    if (lastfragment != 0) {
                        switchFragment(lastfragment, 0);
                        lastfragment = 0;

                    }

                    return true;
                }
                case R.id.project: {
                    if (lastfragment != 1) {
                        switchFragment(lastfragment, 1);
                        lastfragment = 1;

                    }

                    return true;
                }
                case R.id.offical_accounts: {
                    if (lastfragment != 2) {
                        switchFragment(lastfragment, 2);
                        lastfragment = 2;

                    }

                    return true;
                }


            }


            return false;
        }
    };

    //切换Fragment
    private void switchFragment(int lastfragment, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastfragment]);//隐藏上个Fragment
        if (fragments[index].isAdded() == false) {
            transaction.add(R.id.mainview, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();


    }

    @OnClick({R.id.button, R.id.button2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                if (editText2.getText().toString().equals("gao") && editText3.getText().toString().equals("123456")) {
                    editor.putBoolean("isFirstRun", false);
                    editor.commit();
                    editText2.setVisibility(View.GONE);
                    editText3.setVisibility(View.GONE);
                    button.setVisibility(View.GONE);
                    textView3.setVisibility(View.VISIBLE);
                    button2.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(baseActivity, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button2:
                editor.putBoolean("isFirstRun", true);
                editor.commit();
                editText2.setVisibility(View.VISIBLE);
                editText3.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //    @OnClick(R.id.button)
//    public void onViewClicked() {
//        if (editText2.getText().toString().equals("gao") && editText3.getText().toString().equals("123456")) {
//            editText2.setVisibility(View.GONE);
//            editText3.setVisibility(View.GONE);
//            button.setVisibility(View.GONE);
//            textView3.setVisibility(View.VISIBLE);
//            button2.setVisibility(View.VISIBLE);
//        } else {
//            Toast.makeText(baseActivity, "用户名或密码错误", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @OnClick(R.id.button2)
//    public void onViewClicked() {
//    }
}
