package com.client.huaccount.account;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.client.huaccount.R;
import com.client.huaccount.base.BaseActivity;
import com.client.huaccount.bean.LoginInfo;
import com.client.huaccount.configure.ConstantInfo;
import com.client.huaccount.http.MyCallBack;
import com.client.huaccount.http.UserRequest;
import com.client.huaccount.util.UserUtil;

import java.util.List;

/**
 * Created by l on 2018/8/1.
 */

public class RegisterActivity extends BaseActivity {
    private static final String TAG = "RegisterActivity";

    private EditText   mUsername_Edit = null;
    private EditText   mPassword_Edit = null;
    private EditText   mPassword_to_Edit = null;
    private Button     mRegister_button = null;
    private ProgressDialog mProgress;
    private String account;
    private String password;
    private String to_password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);
        setupBackAsUp(R.string.user_register);
        mUsername_Edit = (EditText)findViewById(R.id.et_user_name);
        mPassword_Edit = (EditText)findViewById(R.id.et_user_password);
        mPassword_to_Edit = (EditText)findViewById(R.id.et_to_user_password);
        mRegister_button = (Button)findViewById(R.id.btn_register);

        initListener();
    }

    private void initListener() {
        mRegister_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick");
                   account = mUsername_Edit.getText().toString().trim();
                   password = mPassword_Edit.getText().toString().trim();
                   to_password = mPassword_to_Edit.getText().toString().trim();

                   if(UserUtil.CheckRegister(RegisterActivity.this,account, password, to_password)){
                    sendCheckRequest();
                   }
            }
        });
    }

    private void sendCheckRequest() {
        Log.d(TAG, "sendCheckRequest");
        mProgress = new ProgressDialog(RegisterActivity.this);
        mProgress.setMessage(getResources().getString(R.string.user_being_register));
        mProgress.setCancelable(true);
        mProgress.setCanceledOnTouchOutside(false);
        mProgress.show();
        UserRequest.userRegister(account, password,0x001, myCallBack);
    }

    private MyCallBack myCallBack = new MyCallBack() {
        @Override
        public void onSuccess(int what, Object result) {
            Log.d(TAG, "onSuccess"+ result.toString());
            Toast.makeText(RegisterActivity.this, getText(R.string.user_register_success), Toast.LENGTH_SHORT).show();
            ConstantInfo.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 1000);
            mProgress.dismiss();
        }

        @Override
        public void onSuccessList(int what, List results) {
            Log.d(TAG, "onSuccessList"+ results.toString());
        }

        @Override
        public void onFail(int what, String result) {
            Log.d(TAG, "onFail"+ result.toString());
            mProgress.dismiss();
            switch (what){
                case 1:
                    Toast.makeText(RegisterActivity.this, getText(R.string.account_register_success), Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(RegisterActivity.this, getText(R.string.gank_get_data_fail), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }
}
